package com.lege.officialcn.dao;

import com.lege.mybatisIntroductionCase.domain.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface UserDao {


    /**
     * 根据用户id查询用户
     * @param id
     * @return
     */
    @Select("select * from user where id=#{id}")
    @Results(id="userResultMap",value = {
            @Result(id=true,column = "id",property = "id",jdbcType= JdbcType.BIGINT),
            @Result(id=true,column = "username",property = "username",jdbcType=JdbcType.VARCHAR),
            @Result(id=true,column = "birthday",property = "birthday",jdbcType=JdbcType.DATE),
            @Result(id=true,column = "sex",property = "sex",jdbcType=JdbcType.VARCHAR),
            @Result(id=true,column = "address",property = "address",jdbcType=JdbcType.VARCHAR),
    })
    public User selectById(String id);
    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from user")
    @ResultMap("userResultMap")
    public List<User> findAll();

}
