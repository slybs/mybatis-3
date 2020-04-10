package com.lege.extend.use.plugins.subtable.convert;

import net.sf.jsqlparser.statement.Statement;

/**
 * SQL转换器
 * @author tenglege
 *
 */
public interface SqlConverter {
	
	/**
	 * 转换
	 * @param statement JSqlParser Statement对象（Insert、Update、Delete、Select）
	 * @param params 调用MyBatis Mapper传入的参数
	 * @param mapperId 命名空间 + Mapper id
	 * @return
	 */
	String convert(Statement statement, Object params, String mapperId);
	
}
