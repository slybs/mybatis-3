package com.lege.extend.use.plugins.subtable.convert;

import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.update.Update;

/**
 * update语句转换器
 * @author tenglege
 *
 */
public class UpdateSqlConverter extends AbstractSqlConverter {
	@Override
	public Statement doConvert(final Statement statement, final Object params) {
		Update update = (Update) statement;
		Table table = update.getTable();
		String baseTableName = table.getName();
		table.setName(super.getFinalTable(baseTableName, params));
		return statement;
	}

	//@Override
	//public Statement doConvert(final Statement statement, final Object params) {
	//	Update update = (Update) statement;
	//	Iterator<Table> iterator = update.getTables().iterator();
	//	while(iterator.hasNext()) {
	//		Table table = iterator.next();
	//		String baseTableName = table.getName();
	//		table.setName(super.getFinalTable(baseTableName, params));
	//	}
	//	return statement;
	//}
	
}
