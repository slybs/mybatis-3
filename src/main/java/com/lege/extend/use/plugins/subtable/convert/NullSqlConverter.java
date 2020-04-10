package com.lege.extend.use.plugins.subtable.convert;

import net.sf.jsqlparser.statement.Statement;

/**
 * 空转换器
 * @author tenglege
 *
 */
public class NullSqlConverter extends AbstractSqlConverter {

	@Override
	public Statement doConvert(final Statement statement, final Object params) {
		return statement;
	}
	
}
