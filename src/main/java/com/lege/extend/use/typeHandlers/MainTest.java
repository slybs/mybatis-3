package com.lege.extend.use.typeHandlers;

import com.lege.extend.use.dao.HobbyMapper;
import com.lege.extend.use.dao.IUserDao;
import com.lege.extend.use.domain.Hobby;
import com.lege.extend.use.domain.User;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @Author 了个
 * @date 2020/1/8 11:07
 *
 *
 *
 *
 * 控制台输出：
 *  Hobby{id=3, hobbys=[旅游, 摄影, 读书]}
 *  旅游
 *  摄影
 *  读书
 */
public class MainTest {
    public static void main(String[] args) {
        SqlSession session = SqlSessionUtil.getSession();
        IUserDao mapper = session.getMapper(IUserDao.class);
        User user = mapper.getUserById(2);
        //User{id=2, username='ttt', birthday=Mon Jul 01 14:20:18 GMT+08:00 2019, sex='女', address='北京市'}
        //User{id=2, username='ttt-->test1', birthday=Mon Jul 01 14:20:18 GMT+08:00 2019, sex='女-->test1', address='北京市-->test1'}
        System.out.println(user.toString());
    }
    public static void main1(String[] args) {
        SqlSession session = SqlSessionUtil.getSession();
        HobbyMapper mapper = session.getMapper(HobbyMapper.class);
        Hobby hobby = null;
        try {
            hobby = mapper.getHobbyById(3);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        System.out.println(hobby);
        List<String> hobbys = hobby.getHobbys();
        for (String hhhh : hobbys) {
            System.out.println(hhhh);
        }

    }
}
