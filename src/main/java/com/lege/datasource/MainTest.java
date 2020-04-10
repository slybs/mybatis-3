package com.lege.datasource;

import com.lege.officialcn.dao.IUserDao;
import com.lege.officialcn.domain.User;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @Author 了个
 * @date 2020/1/7 17:29
 */
public class MainTest {
    public static void main(String[] args) throws Exception{
        testSingleDataSource();
    }
    private static void testSingleDataSource() {


        SqlSession sqlSession = SqlSessionUtil.getSession(true);
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        List<User> all = mapper.findAll();
        System.out.println(all.size());
        sqlSession.commit();
        SqlSessionUtil.closeSession();

    }



}
