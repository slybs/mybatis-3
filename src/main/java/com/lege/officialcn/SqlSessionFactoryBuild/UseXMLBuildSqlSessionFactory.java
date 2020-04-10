package com.lege.officialcn.SqlSessionFactoryBuild;

//import com.lege.dao.IUserDao;
//import com.lege.domain.User;

import com.lege.officialcn.dao.IUserDao;
import com.lege.officialcn.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 了个
 * @date 2020/1/7 17:35
 */
public class UseXMLBuildSqlSessionFactory {
    public static void main(String[] args) throws Exception{
        SqlSessionFactory sqlSessionFactory1 = buildSqlSessionFactory();
        SqlSession sqlSession1 = sqlSessionFactory1.openSession();
        List<Object> objects1 = null;
        try {
            objects1 = sqlSession1.selectList("com.lege.dao.IUserDao.findAll");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sqlSession1.close();
        }
        System.out.println(objects1.size());


        SqlSessionFactory sqlSessionFactory2 = getSqlSessionFactory();
        SqlSession sqlSession2 = sqlSessionFactory2.openSession();
        List<Object> objects2 = null;
        try {
            objects2 = sqlSession2.selectList("com.lege.dao.IUserDao.findAll");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(objects2.size());

        SqlSessionFactory sqlSessionFactory3 = getSqlSessionFactory();
        SqlSession sqlSession3= sqlSessionFactory3.openSession();
        List<User> userList = new ArrayList<>();
        try {
            userList = sqlSession3.getMapper(IUserDao.class).findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sqlSession3.close();
        }
        System.out.println("3==>"+userList.size());

    }

    /**
     * 构建SqlSessionFactory
     * @return SqlSessionFactory
     * @throws Exception
     */
    public static SqlSessionFactory buildSqlSessionFactory() throws Exception{
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }
    /**
     * 获取SqlSessionFactory
     * @return SqlSessionFactory
     */
    public static SqlSessionFactory getSqlSessionFactory() {
        String resource = "mybatis-config.xml";
        InputStream is = UseXMLBuildSqlSessionFactory.class.getClassLoader().getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        return sqlSessionFactory;
    }
}
