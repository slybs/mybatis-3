package com.lege.extend.use.plugins.customPlugin02_shardtable01;

/**
 * @Author 了个
 * @date 2020/1/8 17:33
 */
// 分表的策略类
public interface ITableShardStrategy {
    String tableShard(String tableName);
}
