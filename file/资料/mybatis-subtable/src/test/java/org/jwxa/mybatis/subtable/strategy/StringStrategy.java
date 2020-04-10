package org.jwxa.mybatis.subtable.strategy;

import org.jwxa.mybatis.subtable.entity.Device;
import org.jwxa.mybatis.subtable.utils.StrategyUtil;

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
