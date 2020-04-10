package com.lege.extend.use.plugins.subtable.convert;

import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;

/**
 * delete语句转换器
 * @author tenglege
 *
 */
public class DeleteSqlConverter extends AbstractSqlConverter {

	@Override
	public Statement doConvert(final Statement statement, final Object params) {
		Delete delete = (Delete) statement;
		Table table = delete.getTable();
		String baseTableName = table.getName();
		table.setName(super.getFinalTable(baseTableName, params));
		return statement;
	}
	
}
