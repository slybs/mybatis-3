package com.lege.extend.use.plugins.customPlugin01;


import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.Properties;

/**
 * @Author 了个
 * @date 2020/1/8 16:24
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class}) })
public class SQLStatsInterceptor implements Interceptor {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();

        MetaObject metaObj= SystemMetaObject.forObject(statementHandler);
        //获取sql
        String sql1 = (String)metaObj.getValue("delegate.bound.sql");
        //添加limit条件
        sql1="select * from (" + sql1 + ") limit 1000";
        //重新设置sql
        metaObj.setValue("delegate.bound.sql",sql1);


        BoundSql boundSql = statementHandler.getBoundSql();
        String sql = boundSql.getSql();
        System.out.printf("mybatis intercept sql:%s \n",sql);

        logger.info("mybatis intercept sql:{}", sql);
        return invocation.proceed();
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

