package com.lege.extend.use.plugins.subtable.convert;

import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.util.deparser.SelectDeParser;

/**
 * select语句转换器
 * @author tenglege
 *
 */
public class SelectSqlConverter extends AbstractSqlConverter {

	@Override
	public Statement doConvert(final Statement statement, final Object params) {
		Select select = (Select) statement;
		SelectDeParser deParser = new SelectDeParser(){
			@Override
			public void visit(Table tableName) {
				tableName.setName(getFinalTable(tableName.getName(), params));
				super.visit(tableName);
			}
		};
		select.getSelectBody().accept(deParser);
		return statement;
	}
	
}
