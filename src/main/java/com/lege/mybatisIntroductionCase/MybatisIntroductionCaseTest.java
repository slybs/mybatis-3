package com.lege.mybatisIntroductionCase;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.lege.mybatisIntroductionCase.dao.IUserDao;
import com.lege.mybatisIntroductionCase.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisIntroductionCaseTest {

    public static void main(String[] args) throws Exception{
        test01();
    }
    /**
     * 00.原生MyBatis测试查询操作
     * @throws Exception
     */
    private static void test01() throws Exception{
        //1.读取配置文件：E:\tyy\javaprogram\bangcomMybatis\MybatisBasic\target\classes\SqlMapConfig.xml
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //E:\tyy\javaprogram\bangcomMybatis\MybatisBasic\target\classes\mapper\IUserDao.xml
        //InputStream in1= Resources.getResourceAsStream("mapper/IUserDao.xml");
        //是到E:\tyy\javaprogram\bangcomMybatis\MybatisBasic\target\classes\目录下找不到：java.io.IOException: Could not find resource IUserDao.xml
        //InputStream in2= Resources.getResourceAsStream("IUserDao.xml");

        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.build(in);
        //3.使用工厂生产SqlSession对象
        SqlSession session = sqlSessionFactory.openSession();

        //4.使用SqlSession创建Dao接口的代理对象
        IUserDao userDao = session.getMapper(IUserDao.class);
        System.out.println(userDao.toString());
        //5.使用代理对象执行方法
        List<User> users = userDao.findAll();
        for(User user : users){
            System.out.println(user);
        }
        //6.释放资源
        session.close();
        in.close();
    }


}
