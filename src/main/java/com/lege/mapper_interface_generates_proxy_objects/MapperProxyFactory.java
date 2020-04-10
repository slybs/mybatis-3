package com.lege.mapper_interface_generates_proxy_objects;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author 了个
 * @date 2020/4/7 15:35
 */


public class MapperProxyFactory<T> {

    private final Class<T> mapperInterface;
    private final Map<Method, MapperMethod> mapperMethodCache = new ConcurrentHashMap<>();

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public Class<T> getMapperInterface() {
        return mapperInterface;
    }

    public Map<Method, MapperMethod> getMethodCache() {
        return mapperMethodCache;
    }

    protected T newInstance(MapperProxy<T> mapperProxy){
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, mapperProxy);
    }

    public T newInstance(){
        final MapperProxy<T> mapperProxy = new MapperProxy<>(mapperInterface, mapperMethodCache);
        return newInstance(mapperProxy);
    }
}
