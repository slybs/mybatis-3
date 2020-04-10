package com.lege.officialcn.dao.resultMapTestDao;

import  com.lege.officialcn.XMLMappingSqlFile.SqlVo;
import  com.lege.officialcn.domain.resultMapTestDomain.MapperUser;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;

/**
 * @Author 了个
 * @date 2020/1/13 10:09
 */
public interface MapperUserDao {
    public MapperUser select(@Param("UserId") int userId);
    public MapperUser select2(@Param("UserId") int userId);
    public MapperUser select3(@Param("UserId") int userId);

    public void insert(MapperUser mapperUser);
    public void insert2(MapperUser mapperUser);
    public void update(MapperUser mapperUser);

    public void insert3(MapperUser mapperUser);
    public void insert4(HashMap<String, Object> map);
    public void insert5(SqlVo sqlVo);
    public MapperUser selectWithSQL(HashMap<String, Object> map);
    public MapperUser getUserById(@Param("ID") int userid);

}
