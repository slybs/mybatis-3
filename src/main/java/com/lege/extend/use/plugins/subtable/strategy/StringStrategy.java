package com.lege.extend.use.plugins.subtable.strategy;


import com.lege.extend.use.plugins.subtable.StrategyUtil;
import com.lege.extend.use.plugins.subtable.entity.Device;

public class StringStrategy implements Strategy {

	@Override
	public String getFinalTable(String baseTableName, Object params) {
		String flag = null;
		if(params instanceof Device) {
			flag = ((Device)params).getImei();
		}
		return StrategyUtil.getHashTable(baseTableName, "_", flag, 10);
	}

}
