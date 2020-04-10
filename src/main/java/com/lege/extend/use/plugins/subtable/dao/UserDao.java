package com.lege.extend.use.plugins.subtable.dao;



import com.lege.extend.use.plugins.subtable.annotation.SubTable;
import com.lege.extend.use.plugins.subtable.annotation.SubTableIgnore;
import com.lege.extend.use.plugins.subtable.entity.User;
import com.lege.extend.use.plugins.subtable.strategy.LongStrategy;

import java.util.List;

@SubTable(strategyClass= LongStrategy.class)
public interface UserDao {

	void addUser(User user);
	
	@SubTableIgnore
	List<User> getList();
	
	User getUserById(Long id);
}
