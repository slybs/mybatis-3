package com.lege.datasource.HikariCPDataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

/**
 * @Author 了个
 * @date 2020/4/7 18:21
 */
public class HikariCPDataSourceFactory extends UnpooledDataSourceFactory {
    public HikariCPDataSourceFactory(){
        //这里的propertyFileName的取值要为：/datasource/hikariPool.properties，写成这样 datasource/hikariPool.properties 是有问题的
        HikariConfig config = new HikariConfig("/datasource/hikariPool.properties");
        config.setMaximumPoolSize(5);
        this.dataSource = new HikariDataSource(config);
    }
}
