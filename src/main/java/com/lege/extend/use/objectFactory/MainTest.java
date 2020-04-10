package com.lege.extend.use.objectFactory;

import com.lege.extend.use.dao.IUserDao;
import com.lege.extend.use.domain.User;
import org.apache.ibatis.session.SqlSession;

/**
 * @Author 了个
 * @date 2020/1/8 11:07
 */
public class MainTest {
    public static void main(String[] args) {
        SqlSession session = SqlSessionUtil.getSession();
        IUserDao mapper = session.getMapper(IUserDao.class);
        User user = mapper.getUserById(2);
        //User{id=2, username='ttt-->test1', birthday=Mon Jul 01 14:20:18 GMT+08:00 2019, sex='女-->test1', address='北京市-->test1',
        // sysId='设置本运行环境的id：123'}
        //TODO 输出多了上面这个：sysId='设置本运行环境的id：123'
        System.out.println(user.toString());
    }
}
