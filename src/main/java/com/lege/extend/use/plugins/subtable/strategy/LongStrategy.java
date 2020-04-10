package com.lege.extend.use.plugins.subtable.strategy;


import com.lege.extend.use.plugins.subtable.StrategyUtil;
import com.lege.extend.use.plugins.subtable.entity.User;

public class LongStrategy implements Strategy {
	public static void main(String[] args) {

	}
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
