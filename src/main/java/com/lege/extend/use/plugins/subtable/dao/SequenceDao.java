package com.lege.extend.use.plugins.subtable.dao;


import com.lege.extend.use.plugins.subtable.entity.Sequence;

public interface SequenceDao {

	int getNextId(Sequence sequence);
	
}
