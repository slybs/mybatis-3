package com.lege.officialcn.SqlSessionFactoryBuild;

import com.lege.mybatisIntroductionCase.domain.User;
import com.lege.officialcn.dao.UserDao;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.util.List;

/**
 * @Author 了个
 * @date 2020/1/7 17:53
 */
public class NotUseXMLBuildSqlSessionFactory {
    public static void main(String[] args) {
        //创建连接池
        DataSource dataSource = new PooledDataSource(
                "com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/mybatis",
                "root",
                "root");
        //事务
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        //创建环境
        Environment environment = new Environment("development", transactionFactory, dataSource);
        //创建配置
        Configuration configuration = new Configuration(environment);
        //开启驼峰规则
        //configuration.setMapUnderscoreToCamelCase(true);
        //加入资源: Mapper接口
        configuration.addMapper(UserDao.class);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        SqlSession session = sqlSessionFactory.openSession();
        UserDao mapper = session.getMapper(UserDao.class);
        System.out.println(mapper);
        try {
            List<User> userList = mapper.findAll();
            System.out.println(userList.size());
            //TODO:这样不行，操作起来
            //List<User> objects1 = session.selectOne("com.lege.officialcn.SqlSessionFactoryBuild.dao.findAll");
            //System.out.println(objects1.size());

            User user = mapper.selectById("1");
            System.out.println(user.getUsername());

            //操作数据时，需要有提交操作
            session.commit();

        } finally {
            session.close();
        }

    }
}
