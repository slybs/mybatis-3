package org.jwxa.mybatis.subtable.dao;

import org.jwxa.mybatis.subtable.annotation.SubTable;
import org.jwxa.mybatis.subtable.entity.Device;
import org.jwxa.mybatis.subtable.strategy.StringStrategy;

@SubTable(strategyClass=StringStrategy.class)
public interface DeviceDao {

	void addDevice(Device device);
	
}
