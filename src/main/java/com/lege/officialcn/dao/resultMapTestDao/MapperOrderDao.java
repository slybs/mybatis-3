package com.lege.officialcn.dao.resultMapTestDao;

import  com.lege.officialcn.domain.resultMapTestDomain.MapperOrder;
import  com.lege.officialcn.domain.resultMapTestDomain.MapperUser;
import org.apache.ibatis.annotations.Param;

/**
 * @Author 了个
 * @date 2020/1/13 10:12
 */
public interface MapperOrderDao {
    public void insert(MapperOrder mapperOrder);
    public MapperOrder select(@Param("OrderId") int OrderId);
    public MapperOrder select2(@Param("OrderId") int OrderId);
}
