package com.lege.officialcn.datasource;

import com.lege.officialcn.SqlSessionUtil.MultiDataSourceSqlSessionUtil;
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


        SqlSession sqlSession = SqlSessionUtil.getSession(false);
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        List<User> all = mapper.findAll();
        System.out.println(all.size());
        System.out.println(all);
        sqlSession.commit();
        SqlSessionUtil.closeSession();

    }



}
