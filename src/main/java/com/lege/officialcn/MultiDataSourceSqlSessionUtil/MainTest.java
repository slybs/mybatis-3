package com.lege.officialcn.MultiDataSourceSqlSessionUtil;

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
        testMultiDataSource();
    }
    private static void testSingleDataSource() {


        SqlSession sqlSession = MultiDataSourceSqlSessionUtil.getSession("DBServer02");
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        List<User> all = mapper.findAll();
        System.out.println(all.size());


        SqlSession sqlSession2 = MultiDataSourceSqlSessionUtil.getSession("DBServer01");
        IUserDao mapper2 = sqlSession2.getMapper(IUserDao.class);
        List<User> all2 = mapper2.findAll();
        System.out.println(all2.size());


        SqlSession sqlSession3 = MultiDataSourceSqlSessionUtil.getSession("DBServer01");
        IUserDao mapper3 = sqlSession3.getMapper(IUserDao.class);
        List<User> all3 = mapper3.findAll();
        System.out.println(all3.size());

    }
    private static void testMultiDataSource() {
    }


}
