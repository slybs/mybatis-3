package org.jwxa.mybatis.subtable.test;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.jwxa.mybatis.subtable.dao.DeviceDao;
import org.jwxa.mybatis.subtable.entity.Device;

public class DeviceDaoTest {
	
	private SqlSessionFactory sqlSessionFactory;

	@Before
	public void sqlSessionFactory() {
		try {
			Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void addDevice() {
		if(sqlSessionFactory != null) {
			SqlSession session = sqlSessionFactory.openSession();
			DeviceDao dao = session.getMapper(DeviceDao.class);
			try {
				// 添加设备
				for (int i = 30; i < 60; i++) {
					Device device = new Device();
					device.setImei("8888880299966" + i);
					dao.addDevice(device);
				}
				session.commit();
			}finally {
				session.close();
			}
		}
	}
	
}
