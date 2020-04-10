package com.lege.extend.use.plugins.customPlugin02_shardtable02;

import com.lege.extend.use.plugins.subtable.convert.SqlConverterFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.Properties;

/**
 * MyBatis分表插件
 * @author tenglege
 *
 */
@Intercepts({
		@Signature(
				type = StatementHandler.class,
				method = "prepare",
				args = { Connection.class, Integer.class }
		)
})public class SubTablePlugin implements Interceptor {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private static final ReflectorFactory defaultReflectorFactory = new DefaultReflectorFactory();
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		if(invocation.getTarget().getClass().isAssignableFrom(RoutingStatementHandler.class)) {
			//final RoutingStatementHandler statementHandler = (RoutingStatementHandler) ivk.getTarget();
			//final BaseStatementHandler delegate = (BaseStatementHandler) Reflections.getFieldValue(statementHandler, DELEGATE);
			//final MappedStatement mappedStatement = (MappedStatement) Reflections.getFieldValue(delegate, MAPPED_STATEMENT);
			StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
			MetaObject metaObject = MetaObject.forObject(statementHandler,
					SystemMetaObject.DEFAULT_OBJECT_FACTORY,
					SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY,
					defaultReflectorFactory
			);

			MappedStatement mappedStatement = (MappedStatement)metaObject.getValue("delegate.mappedStatement");
            final BoundSql boundSql = statementHandler.getBoundSql();
            String sql = SqlConverterFactory.getGlobalInstance().convert(boundSql, mappedStatement);
            if(StringUtils.isNotBlank(sql)) {
            	logger.debug("[Modified sql] " + sql);
				metaObject.setValue("delegate.boundSql.sql", sql);
            }
		}
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		// 当目标类是StatementHandler类型时，才包装目标类，否者直接返回目标本身, 减少目标被代理的次数
		if (target instanceof StatementHandler) {
			return Plugin.wrap(target, this);
		} else {
			return target;
		}
	}

	@Override
	public void setProperties(Properties properties) {
	}
	
}
