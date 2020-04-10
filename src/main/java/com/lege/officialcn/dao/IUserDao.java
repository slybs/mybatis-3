package com.lege.officialcn.dao;

//import com.lege.domain.User;

import com.lege.officialcn.domain.User;

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

    void addUser(User user);

    List<User> getList();

    User getUserById(Long id);
}
