/**
 * Copyright 2009-2019 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.ibatis.binding;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.ibatis.reflection.ExceptionUtil;
import org.apache.ibatis.session.SqlSession;

/**
 * @author Clinton Begin
 * @author Eduardo Macarron
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {

  private static final long serialVersionUID = -6424540398559729838L;
  private static final int ALLOWED_MODES = MethodHandles.Lookup.PRIVATE | MethodHandles.Lookup.PROTECTED
    | MethodHandles.Lookup.PACKAGE | MethodHandles.Lookup.PUBLIC;
  private static final Constructor<Lookup> lookupConstructor;
  private static final Method privateLookupInMethod;
  //记录了关联的 SqlSession 对象
  private final SqlSession sqlSession;
  //Mapper接口对应的 Class 对象
  private final Class<T> mapperInterface;
  //用于缓存 MapperMethod 对象，其 中 key 是 Mapper 接 口中 方 法对应 的 Method 对象， value 是对应 的
  //MapperMethod 对象。 MapperMethod 对象会完成参数转换以及 SQL 语句的执行功能
  //需要注意的是， MapperMethod 中并不记录任何状态相关的信息，所以 可以在多个代理对象之 间 共享
  private final Map<Method, MapperMethod> methodCache;

  public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface, Map<Method, MapperMethod> methodCache) {
    this.sqlSession = sqlSession;
    this.mapperInterface = mapperInterface;
    this.methodCache = methodCache;
  }

  static {
    Method privateLookupIn;
    try {
      privateLookupIn = MethodHandles.class.getMethod("privateLookupIn", Class.class, MethodHandles.Lookup.class);
    } catch (NoSuchMethodException e) {
      privateLookupIn = null;
    }
    privateLookupInMethod = privateLookupIn;

    Constructor<Lookup> lookup = null;
    if (privateLookupInMethod == null) {
      // JDK 1.8
      try {
        lookup = MethodHandles.Lookup.class.getDeclaredConstructor(Class.class, int.class);
        lookup.setAccessible(true);
      } catch (NoSuchMethodException e) {
        throw new IllegalStateException(
          "There is neither 'privateLookupIn(Class, Lookup)' nor 'Lookup(Class, int)' method in java.lang.invoke.MethodHandles.",
          e);
      } catch (Throwable t) {
        lookup = null;
      }
    }
    lookupConstructor = lookup;
  }

  /**
   *我来说正确答案吧。我们知道这个MapperProxy就是一个InvocationHandler（他的作用是jdk创建动态代理时用的，不清楚动态代理，自己补习一下），
   * 也就是我们会根据当前的接口创建一个这个接口的动态代理对象，使用动态代理对象再利用反射调用实际对象的目标方法。
   *==========================k
   * 然而动态代理对象里面的方法都是Interface规定的。但是动态代理对象也能调用比如toString(),hashCode()等这些方法呀，
   * 这些方法是所有类从Object继承过来的。
   *===============
   * 所以这个判断的根本作用就是，如果利用动态代理对象调用的是toString，hashCode,getClass等这些从Object类继承过来的方法，
   * 就直接反射调用。如果调用的是接口规定的方法。我们就用MapperMethod来执行。
   *
   *
   *
   * 结论：
   *
   * 1）、method.getDeclaringClass用来判断当前这个方法是哪个类的方法。
   * 2）、接口创建出的代理对象不仅有实现接口的方法，也有从Object继承过来的方法
   * 3）、实现的接口的方法method.getDeclaringClass是接口类型，比如com.atguigu.dao.EmpDao
   *         从Object类继承过来的方法类型是java.lang.Object类型
   *
   * 4）、如果是Object类继承来的方法，直接反射调用
   *
   *         如果是实现的接口规定的方法，利用Mybatis的MapperMethod调用
   * @param proxy
   * @param method
   * @param args
   * @return
   * @throws Throwable
   */
  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    try {
      //如采目标方法是来自于继承自 Object的方法：比如toString()等方法 ，则直接调用目标方法
      if (Object.class.equals(method.getDeclaringClass())) {
        //method.invoke("要调用的方法的名字所隶属的对象实体",方法的参数值);
        return method.invoke(this, args);
      } else if (method.isDefault()) {
        if (privateLookupInMethod == null) {
          return invokeDefaultMethodJava8(proxy, method, args);
        } else {
          return invokeDefaultMethodJava9(proxy, method, args);
        }
      }
    } catch (Throwable t) {
      throw ExceptionUtil.unwrapThrowable(t);
    }
    //从缓存中获取 MapperMethod 对象，如采缓存 中没有，则创建新的 MapperMethod 对象并添加到缓存中
    final MapperMethod mapperMethod = cachedMapperMethod(method);
    //调用 MapperMethod.execute()方法执行 SQL 语句
    return mapperMethod.execute(sqlSession, args);
  }

  /**
   * MapperProxy.cachedMapperMethod（）方法主要负责维护 methodCache 这个缓存集合
   * @param method
   * @return
   */
  private MapperMethod cachedMapperMethod(Method method) {
    return methodCache.computeIfAbsent(method,
      k -> new MapperMethod(mapperInterface, method, sqlSession.getConfiguration()));
  }

  private Object invokeDefaultMethodJava9(Object proxy, Method method, Object[] args)
    throws Throwable {
    final Class<?> declaringClass = method.getDeclaringClass();
    return ((Lookup) privateLookupInMethod.invoke(null, declaringClass, MethodHandles.lookup()))
      .findSpecial(declaringClass, method.getName(),
        MethodType.methodType(method.getReturnType(), method.getParameterTypes()), declaringClass)
      .bindTo(proxy).invokeWithArguments(args);
  }

  private Object invokeDefaultMethodJava8(Object proxy, Method method, Object[] args)
    throws Throwable {
    final Class<?> declaringClass = method.getDeclaringClass();
    return lookupConstructor.newInstance(declaringClass, ALLOWED_MODES).unreflectSpecial(method, declaringClass)
      .bindTo(proxy).invokeWithArguments(args);
  }
}
