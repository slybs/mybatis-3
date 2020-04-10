package com.lege.extend.use.plugins.customPlugin02_shardtable01;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.util.Properties;

/**
 * @Author 了个
 * @date 2020/1/8 17:34
 */
@Intercepts({
        @Signature(
                type = StatementHandler.class,
                method = "prepare",
                args = { Connection.class, Integer.class }
        )
})
public class TableShardInterceptor implements Interceptor {

    private static final ReflectorFactory defaultReflectorFactory = new DefaultReflectorFactory();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaObject = MetaObject.forObject(statementHandler,
                SystemMetaObject.DEFAULT_OBJECT_FACTORY,
                SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY,
                defaultReflectorFactory
        );

        MappedStatement mappedStatement = (MappedStatement)metaObject.getValue("delegate.mappedStatement");

        String id = mappedStatement.getId();
        id = id.substring(0, id.lastIndexOf('.'));
        Class clazz = Class.forName(id);

        // 获取TableShard注解
        TableShard tableShard = (TableShard)clazz.getAnnotation(TableShard.class);
        if ( tableShard != null ) {
            String tableName = tableShard.tableName();
            Class<? extends ITableShardStrategy> strategyClazz = tableShard.shardStrategy();
            ITableShardStrategy strategy = strategyClazz.newInstance();
            String newTableName = strategy.tableShard(tableName);
            // 获取源sql
            String sql = (String)metaObject.getValue("delegate.boundSql.sql");
            System.out.printf("分表前的SQL:%s \n" , sql);
            //分表前的SQL:select * from user where id = ?
            // 用新sql代替旧sql, 完成所谓的sql rewrite
            String newSQL = sql.replaceAll(tableName, newTableName);
            metaObject.setValue("delegate.boundSql.sql",newSQL );
            //分表后的SQL:select * from user_20200108 where id = ?
            System.out.printf("分表后的SQL:%s \n" , newSQL);
        }

        // 传递给下一个拦截器处理
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
