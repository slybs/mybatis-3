package com.lege.extend.use.plugins.customPlugin02_shardtable01;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author 了个
 * @date 2020/1/8 17:33
 */
// 按天切分的分表策略类
public class DateTableShardStrategy implements ITableShardStrategy {

    private static final String DATE_PATTERN = "yyyyMMdd";

    @Override
    public String tableShard(String tableName) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        return tableName + "_" + sdf.format(new Date());
    }

}
