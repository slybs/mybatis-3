package com.lege.extend.use.plugins.subtable;

import com.lege.extend.use.plugins.subtable.dao.UserDao;
import com.lege.extend.use.plugins.subtable.entity.User;
import org.apache.ibatis.session.SqlSession;

/**
 * @Author 了个
 * @date 2020/1/8 11:07
 */
public class MainTest {
    public static void main(String[] args) {
        SqlSession session = SqlSessionUtil.getSession();
        UserDao mapper = session.getMapper(UserDao.class);
//        for(int i=1;i<199;i++){
//            mapper.addUser(new User((long) i));
//        }


        User user = mapper.getUserById(2L);
        if(user == null){
            System.out.println("result is null");
        }else {
            System.out.println(user.toString());
        }

        SqlSessionUtil.closeSession();

    }
}
