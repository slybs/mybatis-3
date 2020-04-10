package com.lege.extend.use.plugins.subtable.dao;


import com.lege.extend.use.plugins.subtable.annotation.SubTable;
import com.lege.extend.use.plugins.subtable.entity.Device;
import com.lege.extend.use.plugins.subtable.strategy.StringStrategy;

@SubTable(strategyClass= StringStrategy.class)
public interface DeviceDao {

	void addDevice(Device device);
	
}
