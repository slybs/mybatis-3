package org.apache.ibatis.aaletetest.dao;


import org.apache.ibatis.aaletetest.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户的持久层接口
 */
public interface IUserDao {

    /**
     * 查询所有操作
     * @return
     */
    List<User> findAll();
    User findByName(@Param("username")String username);
    boolean addUser(User user);
}
