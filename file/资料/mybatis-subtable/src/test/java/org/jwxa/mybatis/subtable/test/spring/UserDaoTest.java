package org.jwxa.mybatis.subtable.test.spring;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.jwxa.mybatis.subtable.dao.SequenceDao;
import org.jwxa.mybatis.subtable.dao.UserDao;
import org.jwxa.mybatis.subtable.entity.Sequence;
import org.jwxa.mybatis.subtable.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring-context.xml"}) 
public class UserDaoTest {

	@Autowired
	private UserDao userDao;
	@Autowired
	private SequenceDao sequenceDao;
	
	@Test
	public void addData() {
		Sequence seq = new Sequence();
		
		for (int i = 0; i < 20; i++) {
			User user = new User();
			sequenceDao.getNextId(seq);
			user.setId(seq.getId());
			userDao.addUser(user);
		}
		
	}
	
	@Test
	public void getList() {
		List<User> users = userDao.getList();
		for(User user: users) {
			System.out.println(user.getId());
		}
	}
	
	@Test
	public void getUserById() {
		User user = userDao.getUserById(1l);
		System.out.println(user.getId());
	}
}
