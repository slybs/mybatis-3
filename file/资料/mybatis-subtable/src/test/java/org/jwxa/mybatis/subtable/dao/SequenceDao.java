package org.jwxa.mybatis.subtable.dao;

import org.jwxa.mybatis.subtable.entity.Sequence;

public interface SequenceDao {

	int getNextId(Sequence sequence);
	
}
