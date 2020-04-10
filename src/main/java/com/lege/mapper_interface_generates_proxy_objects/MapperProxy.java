package com.lege.mapper_interface_generates_proxy_objects;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @Author 了个
 * @date 2020/4/7 15:36
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {

    private final Class<T> mapperInterface;
    private final Map<Method, MapperMethod> mapperMethodCache;

    public MapperProxy(Class<T> mapperInterface, Map<Method, MapperMethod> mapperMethodCache) {
        this.mapperInterface = mapperInterface;
        this.mapperMethodCache = mapperMethodCache;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //如果是Object中定义的方法，直接执行。如toString(),hashCode()等
        if (Object.class.equals(method.getDeclaringClass())) {
            try {
                return method.invoke(this, args);
            } catch (Throwable t) {

            }
        }
        //其他Mapper接口定义的方法交由mapperMethod来执行
        final MapperMethod mapperMethod = cachedMapperMethod(method);
        return mapperMethod.execute(args);
    }

    private MapperMethod cachedMapperMethod(Method method) {
        MapperMethod mapperMethod = mapperMethodCache.get(method);
        if (mapperMethod == null) {
            mapperMethod = new MapperMethod(method, this);
            mapperMethodCache.put(method, mapperMethod);
        }
        return mapperMethod;
    }
}
