package com.lege.extend.use.plugins.customPlugin02_shardtable01;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author 了个
 * @date 2020/1/8 17:34
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TableShard {

    // 要替换的表名
    String tableName();

    // 对应的分表策略类
    Class<? extends ITableShardStrategy> shardStrategy();

}
