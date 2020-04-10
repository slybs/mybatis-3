package org.jwxa.mybatis.subtable.strategy;

import org.jwxa.mybatis.subtable.entity.User;
import org.jwxa.mybatis.subtable.utils.StrategyUtil;

public class LongStrategy implements Strategy {

	@Override
	public String getFinalTable(String baseTableName, Object params) {
		Long flag = -1l;
		if(params instanceof User) {
			flag = ((User)params).getId();
		}else if(params instanceof Long) {
			flag = (Long) params;
		}
		return StrategyUtil.getHashTable(baseTableName, "_", flag, 10);
	}

}
