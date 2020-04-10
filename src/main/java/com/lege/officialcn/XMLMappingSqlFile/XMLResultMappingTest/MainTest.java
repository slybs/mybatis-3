package com.lege.officialcn.XMLMappingSqlFile.XMLResultMappingTest;

import com.lege.officialcn.XMLMappingSqlFile.XMLResultMappingTest.SqlSessionUtil;

import com.lege.officialcn.dao.resultMapTestDao.MapperOrderDao;
import com.lege.officialcn.dao.resultMapTestDao.MapperUserDao;

import com.lege.officialcn.domain.resultMapTestDomain.MapperOrder;
import com.lege.officialcn.domain.resultMapTestDomain.MapperUser;
import org.apache.ibatis.session.SqlSession;


/**
 * @Author 了个
 * @date 2020/1/7 17:29
 * 说明==================>
 *resultType
 *      resultType可以把查询结果封装到pojo类型中，但必须pojo类的属性名和查询到的数据库表的字段名一致。
 *      如果sql查询到的字段与pojo的属性名不一致，则需要使用resultMap将字段名和属性名对应起来，进行手动配置封装，将结果映射到pojo中
 *
 * resultMap
 *      resultMap可以实现将查询结果映射为复杂类型的pojo，比如在查询结果映射对象中包括pojo和list实现一对一查询和一对多查询。
 *
 */
public class MainTest {
    public static void main(String[] args) throws Exception{
        //testInsert();//主键冲突时进行更新替换
        //testInsert2();//主键冲突时直接抛异常
        //testSelect();
//        testSelect2();
        //insertOrder();
        selectOrder();
//        testSelect3();
        select2Order();
    }

    private static void select2Order() {
        SqlSession sqlSession = SqlSessionUtil.getSession(false);
        MapperOrderDao mapper = sqlSession.getMapper(MapperOrderDao.class);
        MapperOrder order = mapper.select2(221);
        System.out.println(order.toString());
    }

    private static void testSelect3() {
        SqlSession sqlSession = SqlSessionUtil.getSession(false);
        MapperUserDao mapper = sqlSession.getMapper(MapperUserDao.class);
        MapperUser user = mapper.select3(22);
        System.out.println(user.toString());
    }

    private static void selectOrder() {
        SqlSession sqlSession = SqlSessionUtil.getSession(false);
        MapperOrderDao mapper = sqlSession.getMapper(MapperOrderDao.class);
        MapperOrder order = mapper.select(221);
        System.out.println(order.toString());
    }

    private static void insertOrder() {

        SqlSession sqlSession = SqlSessionUtil.getSession(false);
        MapperOrderDao mapper = sqlSession.getMapper(MapperOrderDao.class);
//        MapperOrder mapperOrder = new MapperOrder();
//        mapperOrder.setOrderId(221);
//        mapperOrder.setUserId(22);
//        mapperOrder.setProductId(2211);
//        mapper.insert(mapperOrder);
//        sqlSession.commit();
        MapperOrder mapperOrder1 = new MapperOrder();
        mapperOrder1.setOrderId(222);
        mapperOrder1.setUserId(22);
        mapperOrder1.setProductId(2222);
        mapper.insert(mapperOrder1);
        sqlSession.commit();
        SqlSessionUtil.closeSession();
    }

    private static void testSelect2() {
        SqlSession sqlSession = SqlSessionUtil.getSession(false);
        MapperUserDao mapper = sqlSession.getMapper(MapperUserDao.class);
        MapperUser user = mapper.select2(22);
        System.out.println(user.toString());

    }
    private static void testSelect() {
        SqlSession sqlSession = SqlSessionUtil.getSession(false);
        MapperUserDao mapper = sqlSession.getMapper(MapperUserDao.class);
        MapperUser user = mapper.select(22);
        System.out.println(user.toString());

    }

    private static void testInsert() {
        SqlSession sqlSession = SqlSessionUtil.getSession(false);
        MapperUserDao mapper = sqlSession.getMapper(MapperUserDao.class);
        MapperUser mapperUser = new MapperUser();
        mapperUser.setUserId(22);
        mapperUser.setUserName("22");
        mapperUser.setUserAddress("222");
        mapper.insert(mapperUser);
        sqlSession.commit();
        SqlSessionUtil.closeSession();
    }

    private static void testInsert2() {
        SqlSession sqlSession = SqlSessionUtil.getSession(false);
        MapperUserDao mapper = sqlSession.getMapper(MapperUserDao.class);
        MapperUser mapperUser = new MapperUser();
        mapperUser.setUserId(22);
        mapperUser.setUserName("22");
        mapperUser.setUserAddress("222");
        mapper.insert2(mapperUser);
        sqlSession.commit();
        SqlSessionUtil.closeSession();
    }



}
