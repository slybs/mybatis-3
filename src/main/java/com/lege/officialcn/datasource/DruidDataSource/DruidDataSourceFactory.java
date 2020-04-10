package com.lege.officialcn.datasource.DruidDataSource;

/**
 * @Author 了个
 * @date 2020/1/10 9:31
 */
import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

public class DruidDataSourceFactory implements DataSourceFactory {
    private  Properties props;
    @Override
    public void setProperties(Properties props) {
        this.props = props;
    }

    @Override
    public DataSource getDataSource() {
        DruidDataSource dds =  new DruidDataSource();
        dds.setDriverClassName(this.props.getProperty("driver"));
        dds.setUrl(this.props.getProperty("url"));
        dds.setPassword(this.props.getProperty("password"));
        dds.setUsername(this.props.getProperty("username"));
        //其他配置可以根据MyBatis主配置文件进行配置

        //<!-- 配置初始化大小、最小、最大 -->
        //<property name="initialSize" value="1" />
        //<property name="minIdle" value="1" />
        //<property name="maxActive" value="20" />
        //
        //<!-- 配置获取连接等待超时的时间 -->
        //<property name="maxWait" value="60000" />
        //<!-- 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒 -->
        //<property name="timeBetweenEvictionRunsMillis" value="3000" />
        //<!-- 配置一个连接在池中最小生存的时间，单位是毫秒 -->
        //<property name="minEvictableIdleTimeMillis" value="300000" />
        //
        //<property name="validationQuery" value="SELECT 'x' FROM DUAL" />
        //<property name="testWhileIdle" value="true" />
        //<property name="testOnBorrow" value="false" />
        //<property name="testOnReturn" value="false" />
        //<!-- mysql 不支持 poolPreparedStatements-->
        //<!--<property name="poolPreparedStatements" value="true" />-->
        //<!--<property name="maxPoolPreparedStatementPerConnectionSize" value="20" />-->
        //
        //<!-- 开启Druid的监控统计功能  掉后监控界面sql无法统计-->
        //<property name="filters" value="stat" />

        dds.setInitialSize(Integer.valueOf(this.props.getProperty("initialSize")));
        dds.setMinIdle(Integer.valueOf(this.props.getProperty("minIdle")));
        dds.setMaxActive(Integer.valueOf(this.props.getProperty("maxActive")));

        dds.setMaxWait(Long.valueOf(this.props.getProperty("initialSize")));
        dds.setTimeBetweenEvictionRunsMillis(Long.valueOf(this.props.getProperty("timeBetweenEvictionRunsMillis")));
        dds.setMinEvictableIdleTimeMillis(Long.valueOf(this.props.getProperty("minEvictableIdleTimeMillis")));

        try {
            dds.init();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dds;
    }
}

