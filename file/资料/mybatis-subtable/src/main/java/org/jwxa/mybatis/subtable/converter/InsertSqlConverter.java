package org.jwxa.mybatis.subtable.converter;

import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.insert.Insert;

/**
 * insert into转换器
 * @author tenglege
 *
 */
public class InsertSqlConverter extends AbstractSqlConverter {

	@Override
	public Statement doConvert(final Statement statement, final Object params) {
		Insert insert = (Insert) statement;
		Table table = insert.getTable();
		String baseTableName = table.getName();
		table.setName(super.getFinalTable(baseTableName, params));
		return statement;
	}
	
}
