package org.jwxa.mybatis.subtable.plugin;

import java.sql.Connection;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.jwxa.mybatis.subtable.converter.SqlConverterFactory;
import org.jwxa.mybatis.subtable.utils.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MyBatis分表插件
 * @author tenglege
 *
 */
@Intercepts( { @Signature(
		type = StatementHandler.class, 
		method = "prepare", 
		args = { Connection.class })})
public class SubTablePlugin implements Interceptor {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public static final String DELEGATE = "delegate";
	public static final String MAPPED_STATEMENT = "mappedStatement";
	
	@Override
	public Object intercept(Invocation ivk) throws Throwable {
		if(ivk.getTarget().getClass().isAssignableFrom(RoutingStatementHandler.class)) {
			final RoutingStatementHandler statementHandler = (RoutingStatementHandler) ivk.getTarget();
            final BaseStatementHandler delegate = (BaseStatementHandler) Reflections.getFieldValue(statementHandler, DELEGATE);
            final MappedStatement mappedStatement = (MappedStatement) Reflections.getFieldValue(delegate, MAPPED_STATEMENT);
            final BoundSql boundSql = statementHandler.getBoundSql();
            String sql = SqlConverterFactory.getGlobalInstance().convert(boundSql, mappedStatement);
            if(StringUtils.isNotBlank(sql)) {
            	logger.debug("[Modified sql] " + sql);
            	Reflections.setFieldValue(statementHandler.getBoundSql(), "sql", sql);
            }
		}
		return ivk.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
	}
	
}
