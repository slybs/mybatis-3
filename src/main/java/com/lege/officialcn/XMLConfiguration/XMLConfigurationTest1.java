package com.lege.officialcn.XMLConfiguration;

//import com.lege.dao.IUserDao;
//import com.lege.domain.User;
import com.lege.officialcn.SqlSessionUtil.SqlSessionUtil;
import com.lege.officialcn.dao.IUserDao;
import com.lege.officialcn.domain.User;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 了个
 * @date 2020/1/8 9:23
 */
public class XMLConfigurationTest1 {
    /**
     * 每个线程都应该有它自己的 SqlSession 实例。SqlSession 的实例不是线程安全的，因此是不能被共享的，所以它的最佳的作用域是请求或方法作用域。
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{

        SqlSession sqlSession3= SqlSessionUtil.getSession();
        //sqlSession2=org.apache.ibatis.session.defaults.DefaultSqlSession@4439f31e
        System.out.println("sqlSession3="+sqlSession3);
        List<User> userList = new ArrayList<>();
        try {
            userList = sqlSession3.getMapper(IUserDao.class).findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //SqlSessionUtil.closeSession();
        }
        System.out.println("3==>"+userList.size());


        SqlSession sqlSession1 = SqlSessionUtil.getSession();
        //sqlSession2=org.apache.ibatis.session.defaults.DefaultSqlSession@4439f31e
        System.out.println("sqlSession1="+sqlSession1);
        List<Object> objects1 = null;
        try {
            objects1 = sqlSession1.selectList("com.lege.officialcn.dao.IUserDao.findAll");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //SqlSessionUtil.closeSession();
        }
        System.out.println(objects1.size());


        SqlSession sqlSession2 = SqlSessionUtil.getSession();
        //sqlSession2=org.apache.ibatis.session.defaults.DefaultSqlSession@4439f31e
        System.out.println("sqlSession2="+sqlSession2);
        List<Object> objects2 = null;
        try {
            objects2 = sqlSession2.selectList("com.lege.officialcn.dao.IUserDao.findAll");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //SqlSessionUtil.closeSession();
        }
        System.out.println(objects2.size());

        SqlSessionUtil.closeSession();

    }


    public static void main1(String[] args) throws Exception{
        SqlSession sqlSession1 = SqlSessionUtil.getSession();
        //sqlSession1=org.apache.ibatis.session.defaults.DefaultSqlSession@4439f31e
        System.out.println("sqlSession1="+sqlSession1);
        List<Object> objects1 = null;
        try {
            objects1 = sqlSession1.selectList("com.lege.officialcn.dao.IUserDao.findAll");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            SqlSessionUtil.closeSession();
        }
        System.out.println(objects1.size());


        SqlSession sqlSession2 = SqlSessionUtil.getSession();
        //sqlSession2=org.apache.ibatis.session.defaults.DefaultSqlSession@23e028a9
        System.out.println("sqlSession2="+sqlSession2);
        List<Object> objects2 = null;
        try {
            objects2 = sqlSession2.selectList("com.lege.officialcn.dao.IUserDao.findAll");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            SqlSessionUtil.closeSession();
        }
        System.out.println(objects2.size());

        SqlSession sqlSession3= SqlSessionUtil.getSession();
        //sqlSession3=org.apache.ibatis.session.defaults.DefaultSqlSession@43814d18
        System.out.println("sqlSession3="+sqlSession3);
        List<User> userList = new ArrayList<>();
        try {
            userList = sqlSession3.getMapper(IUserDao.class).findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            SqlSessionUtil.closeSession();
        }
        System.out.println("3==>"+userList.size());

    }

    ///**
    // * 构建SqlSessionFactory
    // * @return SqlSessionFactory
    // * @throws Exception
    // */
    //public static SqlSessionFactory buildSqlSessionFactory() throws Exception{
    //    InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
    //    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    //    return sqlSessionFactory;
    //}
    ///**
    // * 获取SqlSessionFactory
    // * @return SqlSessionFactory
    // */
    //public static SqlSessionFactory getSqlSessionFactory() {
    //    String resource = "mybatis-config.xml";
    //    InputStream is = UseXMLBuildSqlSessionFactory.class.getClassLoader().getResourceAsStream(resource);
    //    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
    //    return sqlSessionFactory;
    //}

}
