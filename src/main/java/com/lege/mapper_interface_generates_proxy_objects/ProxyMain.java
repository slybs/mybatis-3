package com.lege.mapper_interface_generates_proxy_objects;

/**
 * @Author 了个
 * @date 2020/4/7 15:39
 * 输出：
 *    实例化UserDao的代理工厂
 *    mapper_interface_generates_proxy_objects.MapperProxy@270421f5
 *    MapperProxy: mapper_interface_generates_proxy_objects.MapperProxy@270421f5
 *    execute method {add}
 *    MapperProxy: mapper_interface_generates_proxy_objects.MapperProxy@270421f5
 *    execute method {update}
 *
 *    实例化OrderDao的代理工厂
 *    mapper_interface_generates_proxy_objects.MapperProxy@67117f44
 *    MapperProxy: mapper_interface_generates_proxy_objects.MapperProxy@67117f44
 *    execute method {add}
 *    MapperProxy: mapper_interface_generates_proxy_objects.MapperProxy@67117f44
 *    execute method {update}
 */
public class ProxyMain {
    public static void main(String[] args) {
        System.out.println("实例化UserDao的代理工厂");
        MapperProxyFactory<UserDao> userDaoProxyFactory = new MapperProxyFactory<>(UserDao.class);

        UserDao target = userDaoProxyFactory.newInstance();
        System.out.println(target);

        target.add("chinoukin");

        target.update("chenyingqin");


        System.out.println("\n实例化OrderDao的代理工厂");
        MapperProxyFactory<OrderDao> OrderDaoProxyFactory = new MapperProxyFactory<>(OrderDao.class);

        OrderDao target1 = OrderDaoProxyFactory.newInstance();
        System.out.println(target1);

        target1.add("订单1");

        target1.update("订单xxxx");


    }

}
