package com.lege.extend.use.plugins.customPlugin02_shardtable01;



import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.Properties;

/**
 * @Author 了个
 * @date 2020/1/8 16:24
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class}) })
public class SQLStatsInterceptor2 implements Interceptor {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();

        MetaObject metaObj= SystemMetaObject.forObject(statementHandler);
        BoundSql boundSql = (BoundSql) metaObj.getValue("delegate.boundSql");
        // Configuration configuration = (Configuration) metaStatementHandler
        // .getValue("delegate.configuration");
        Object parameterObject = metaObj.getValue("delegate.boundSql.parameterObject");
        doSplitTable(metaObj);
        // 传递给下一个拦截器处理
        return invocation.proceed();

    }

    private void doSplitTable(MetaObject metaStatementHandler) throws ClassNotFoundException{
        String originalSql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
        if (originalSql != null && !originalSql.equals("")) {
            System.out.printf("分表前的SQL:%s \n" , originalSql);
            MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
            String id = mappedStatement.getId();
            String className = id.substring(0, id.lastIndexOf("."));
            Class<?> classObj = Class.forName(className);
            // 根据配置自动生成分表SQL
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        String dialect = properties.getProperty("dialect");
        logger.info("mybatis intercept dialect:{}", dialect);
        System.out.println(dialect);
    }
}

