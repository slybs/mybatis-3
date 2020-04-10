package com.lege.officialcn.JavaAPI;


import com.lege.officialcn.dao.ReasonDao;
import com.lege.officialcn.domain.Reason;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigInteger;

/**
 *       使用 MyBatis 的主要 Java 接口就是 SqlSession。你可以通过这个接口来执行命令，获取映射器和管理事务。我们会概括讨论一下 SqlSession 本身，
 *   但是首先我们还是要了解如何获取一个 SqlSession 实例。SqlSession 是由 SqlSessionFactory 实例创建的。SqlSessionFactory 对象包含创建 SqlSession 实例的所有方法。
 *   而 SqlSessionFactory 本身是由 SqlSessionFactoryBuilder 创建的，它可以从 XML、注解或手动配置 Java 代码来创建 SqlSessionFactory。
 *
 *       注意 当 Mybatis 与一些依赖注入框架（如 Spring 或者 Guice）同时使用时，SqlSession 将被依赖注入框架所创建，所以你不需要使用 SqlSessionFactoryBuilder 或者 SqlSessionFactory，
 *   可以直接看 SqlSession 这一节。请参考 Mybatis-Spring 或者 Mybatis-Guice 手册了解更多信息。
 */
public class MainTest {
    public static void main(String[] args) throws Exception {
        Reader reader = Resources.getResourceAsReader("ReasonMapper/mybatis-config.xml");
        InputStream inputStream = Resources.getResourceAsStream("ReasonMapper/mybatis-config.xml");

        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

        SqlSessionFactory build = sqlSessionFactoryBuilder.build(reader);
        SqlSessionFactory build1 = sqlSessionFactoryBuilder.build(inputStream);

        SqlSession sqlSession = build.openSession(true);
        ReasonDao reasonDao = sqlSession.getMapper(ReasonDao.class);
        Reason reason = reasonDao.findReasonById(new BigInteger("1"));
        System.out.println(reason);


        //testinit();
    }


    private static void testinit() {
        SqlSession sqlSession = SqlSessionUtil.getSession(true);
        ReasonDao reasonDao = sqlSession.getMapper(ReasonDao.class);
        Reason reason = reasonDao.findReasonById(new BigInteger("1"));
        System.out.println(reason);
    }


}
