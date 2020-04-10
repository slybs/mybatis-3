package com.lege.officialcn.mappers;

import com.lege.officialcn.SqlSessionUtil.SqlSessionUtil;
import com.lege.officialcn.dao.IUserDao;
import com.lege.officialcn.dao.UserDao;
import com.lege.officialcn.domain.User;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 了个
 * @date 2020/1/7 17:29
 */
public class MainTest {
    public static void main(String[] args) {
        SqlSession sqlSession3= SqlSessionUtil.getSession();
        //sqlSession3=org.apache.ibatis.session.defaults.DefaultSqlSession@43814d18
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

        UserDao mapper = sqlSession3.getMapper(UserDao.class);
        List<com.lege.mybatisIntroductionCase.domain.User> all = mapper.findAll();
        System.out.println(all.size());
    }
}
