package com.lege.officialcn.XMLMappingSqlFile.InsertAndUpdate;

import com.lege.officialcn.XMLMappingSqlFile.SqlVo;
import com.lege.officialcn.dao.resultMapTestDao.MapperOrderDao;
import com.lege.officialcn.dao.resultMapTestDao.MapperUserDao;
import com.lege.officialcn.domain.resultMapTestDomain.MapperOrder;
import com.lege.officialcn.domain.resultMapTestDomain.MapperUser;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.Map;


/**
 * @Author 了个
 * @date 2020/1/7 17:29
 * 说明==================>
 * resultType
 * resultType可以把查询结果封装到pojo类型中，但必须pojo类的属性名和查询到的数据库表的字段名一致。
 * 如果sql查询到的字段与pojo的属性名不一致，则需要使用resultMap将字段名和属性名对应起来，进行手动配置封装，将结果映射到pojo中
 * <p>
 * resultMap
 * resultMap可以实现将查询结果映射为复杂类型的pojo，比如在查询结果映射对象中包括pojo和list实现一对一查询和一对多查询。、
 *
 * statementType 取值说明： 
 *      1、STATEMENT:直接操作sql，不进行预编译，获取数据：$—Statement 
 *      2、PREPARED:预处理，参数，进行预编译，获取数据：#—–PreparedStatement:默认 
 *      3、CALLABLE:执行存储过程————CallableStatement 
 * ————————————————
 * 版权声明：本文为CSDN博主「上善若泪」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/u012060033/article/details/85678693
 *
 */
public class MainTest {
    public static void main(String[] args) throws Exception {
        //testInsert3();
        //testInsert4();
        //testInsert5();
        selectWithSQL();
        //TODO 测试MyBatis调用存储过程
        testStoredProcedure();
    }

    private static void testStoredProcedure() {
        SqlSession sqlSession = SqlSessionUtil.getSession(false);
        MapperUserDao mapper = sqlSession.getMapper(MapperUserDao.class);
        MapperUser user = mapper.getUserById(55);
        //MapperUser{UserId=55, UserName='User44Name', UserAddress='User44Address', orders=null}
        System.out.println(user.toString());
    }

    private static void selectWithSQL() {
        SqlSession sqlSession = SqlSessionUtil.getSession(false);
        MapperUserDao mapper = sqlSession.getMapper(MapperUserDao.class);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("sqlStr", "SELECT * FROM MapperUser WHERE UserID = 66");
        MapperUser mapperUser = mapper.selectWithSQL(map);
        System.out.println(mapperUser.toString());
    }

    private static void testInsert5() {
        SqlSession sqlSession = SqlSessionUtil.getSession(false);
        MapperUserDao mapper = sqlSession.getMapper(MapperUserDao.class);
        String sql = "INSERT INTO MapperUser (UserID, UserName, UserAddress) VALUES (66, '66-UserName','44-UserAddress')";
        SqlVo sqlVo = new SqlVo();
        sqlVo.setSqlStr(sql);
        mapper.insert5(sqlVo);
        sqlSession.commit();
        SqlSessionUtil.closeSession();
    }

    private static void testInsert3() {
        SqlSession sqlSession = SqlSessionUtil.getSession(false);
        MapperUserDao mapper = sqlSession.getMapper(MapperUserDao.class);
        MapperUser mapperUser = new MapperUser();
        mapperUser.setUserId(33);
        mapperUser.setUserName("33");
        mapperUser.setUserAddress("333");
        mapper.insert3(mapperUser);
        sqlSession.commit();
        SqlSessionUtil.closeSession();
    }

    private static void testInsert4() {
        SqlSession sqlSession = SqlSessionUtil.getSession(false);
        MapperUserDao mapper = sqlSession.getMapper(MapperUserDao.class);
        MapperUser mapperUser = new MapperUser();
        mapperUser.setUserId(22);
        mapperUser.setUserName("22");
        mapperUser.setUserAddress("222");

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("tablename", "MapperUser");
        map.put("UserId", 55);
        map.put("UserName", "'" + "User44Name" + "'");
        map.put("UserAddress", "'" + "User44Address" + "'");
        //SQL: INSERT INTO MapperUser (UserID, UserName, UserAddress)         VALUES (55, 'User44Name','User44Address')
        mapper.insert4(map);
        sqlSession.commit();
        SqlSessionUtil.closeSession();
    }


}
