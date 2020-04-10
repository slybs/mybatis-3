package org.jwxa.mybatis.subtable.dao;

import java.util.List;

import org.jwxa.mybatis.subtable.annotation.SubTable;
import org.jwxa.mybatis.subtable.annotation.SubTableIgnore;
import org.jwxa.mybatis.subtable.entity.User;
import org.jwxa.mybatis.subtable.strategy.LongStrategy;

@SubTable(strategyClass=LongStrategy.class)
public interface UserDao {

	void addUser(User user);
	
	@SubTableIgnore
	List<User> getList();
	
	User getUserById(Long id);
}
