package com.lege.extend.use.objectFactory;

import com.lege.extend.use.domain.User;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * @Author 了个
 * @date 2020/1/8 15:19
 */
public class MyObjectFactory extends DefaultObjectFactory {
    @Override
    public <T> T create(Class<T> type) {
        if (type == User.class) {
            // 如果是用户类 先创建实例
            User user = (User) super.create(type);
            // 在这个时候，user是里面的值是空值 create只是创建新的实例，并没有赋值，而是在后面赋值
            // 所以如果利用objectFactory进行设置实例的值，需要考虑后面赋值会产生覆盖
            // 但是这里可以进行赋值一些数据库不存在的字段
            user.setId(1111111);
            user.setSysId("设置本运行环境的id：123");
            return (T) user;
        }
        return super.create(type);
    }

    @Override
    public <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
        return super.create(type, constructorArgTypes, constructorArgs);
    }

    @Override
    public void setProperties(Properties properties) {
        Iterator iterator = properties.keySet().iterator();
        while (iterator.hasNext()) {
            String keyValue = String.valueOf(iterator.next());
            System.out.println(keyValue+"==>"+properties.getProperty(keyValue));
        }
        //上面输出是：someProperty==>100
        super.setProperties(properties);
    }

}
