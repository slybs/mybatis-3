package com.lege.officialcn.SqlSessionUtil;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**TODO 需要去评估下线程安全性问题
 * @Author 了个
 * @date 2020/1/8 9:15
 */

/**
 * 说明：
 * 其中的Mybatis_config.xml是我的MyBatis的核心配置文件。
 * 用静态代码块进行创建SqlSessionFactory，只在类加载时创建一次，保证了整个程序运行时只有一个工厂实例。
 * 用线程变量保存session对象，是为了线程安全着想，这样自己的线程管理自己线程的session，不会出现多实例同时调用同一个session对象，造成数据不准确的情况出现。
 * MyBatisUtil.getSession(); 即可获得session对象；
 * MyBatisUtil.closeSession();  即可关闭线程对象，务必要在session使用完毕后关闭session。
 */
//public class SqlSessionUtil implements Cloneable{
public class MultiDataSourceSqlSessionUtil {
    /**
     *      SqlSessionFactory 一旦被创建就应该在应用的运行期间一直存在，没有任何理由丢弃它或重新创建另一个实例。
     * 使用 SqlSessionFactory 的最佳实践是在应用运行期间不要重复创建多次，多次重建 SqlSessionFactory 被视为一种代码“坏味道（bad smell）”。
     * 因此 SqlSessionFactory 的最佳作用域是应用作用域。 有很多方法可以做到，最简单的就是使用单例模式或者静态单例模式。
     *
     *      MyBatis 可以配置成适应多种环境，这种机制有助于将 SQL 映射应用于多种数据库之中， 现实情况下有多种理由需要这么做。例如，开发、测试和生产环境需要有不同的配置；
     * 或者想在具有相同 Schema 的多个生产数据库中 使用相同的 SQL 映射。有许多类似的使用场景。
     * 不过要记住：尽管可以配置多个环境，但每个 SqlSessionFactory 实例只能选择一种环境。
     *
     *      所以，如果你想连接两个数据库，就需要创建两个 SqlSessionFactory 实例，每个数据库对应一个。而如果是三个数据库，就需要三个实例，依此类推，记起来很简单：
     * 每个数据库对应一个 SqlSessionFactory 实例
     * 为了指定创建哪种环境，只要将它作为可选的参数传递给 SqlSessionFactoryBuilder 即可。可以接受环境配置的两个方法签名是：
     *      SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader, environment);
     *      SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader, environment, properties);
     *
     */
    private static Map<String,SqlSessionFactory> enviromentSqlSessionFactoryMap = new ConcurrentHashMap<>();
    /*
     * 创建本地线程变量，为每一个线程独立管理一个session对象 每一个线程只有且仅有单独且唯一的一个session对象
     * 加上线程变量对session进行管理，可以保证线程安全，避免多实例同时调用同一个session对象
     * 每一个线程都会new一个线程变量，从而分配到自己的session对象
     * TODO 多数据源的情况下：在一个请求线程内，多个SqlSessionFactory实例创建了多个SqlSession对不同库中表进行操作，会怎么样？
     * 也就是说一个线程ThreadLocal中可能存在多个数据源
     */
    private volatile static ThreadLocal<HashMap<String,SqlSession>> threadMaplocal = new ThreadLocal<HashMap<String,SqlSession>>();


    /**
     * 返回sessionFactory对象 工厂对象
     *
     * @return sessionFactory
     */
    private synchronized static SqlSessionFactory getSessionFactory(String envirment) {
        SqlSessionFactory sqlSessionFactory = enviromentSqlSessionFactoryMap.get(envirment);
        if(sqlSessionFactory != null){
            return sqlSessionFactory;
        }else {
            try {
                Reader reader = Resources.getResourceAsReader("multiDataSource/mybatis-config2.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader,envirment);
                enviromentSqlSessionFactoryMap.put(envirment,sqlSessionFactory);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sqlSessionFactory;
    }

    /**
     * 返回session对象
     * 每个线程都应该有它自己的 SqlSession 实例。SqlSession 的实例不是线程安全的，因此是不能被共享的，所以它的最佳的作用域是请求或方法作用域。
     * 绝对不能将 SqlSession 实例的引用放在一个类的静态域，甚至一个类的实例变量也不行。
     * 也绝不能将 SqlSession 实例的引用放在任何类型的托管作用域中，比如 Servlet 框架中的 HttpSession。
     * 如果你现在正在使用一种 Web 框架，要考虑 SqlSession 放在一个和 HTTP 请求对象相似的作用域中。
     * 换句话说，每次收到的 HTTP 请求，就可以打开一个 SqlSession，返回一个响应，就关闭它。
     * 这个关闭操作是很重要的，你应该把这个关闭操作放到 finally 块中以确保每次都能执行关闭。 下面的示例就是一个确保 SqlSession 关闭的标准模式：
     * try (SqlSession session = sqlSessionFactory.openSession()) {
     *   // 你的应用逻辑代码
     * }
     * 在你的所有的代码中一致地使用这种模式来保证所有数据库资源都能被正确地关闭。
     * @return session
     */
    public synchronized static SqlSession getSession(boolean isAutoCommit,String envirment){
        //优先从线程变量中取session对象
        HashMap<String, SqlSession> sqlSessionthreadlocalMap = threadMaplocal.get();
        SqlSession sqlSession = null;
        if(sqlSessionthreadlocalMap != null){
            sqlSession = sqlSessionthreadlocalMap.get(envirment);
        }
        //如果线程变量中的session为null，
        if(sqlSession==null){
            //新建session会话，并把session放在线程变量中
            newSession(isAutoCommit,envirment);
            //再次从线程变量中取session对象
            sqlSession = threadMaplocal.get().get(envirment);
        }
        return sqlSession;
    }
    public synchronized static SqlSession getSession(String envirment){
        //优先从线程变量中取session对象
        HashMap<String, SqlSession> sqlSessionthreadlocalMap = threadMaplocal.get();
        SqlSession sqlSession = null;
        if(sqlSessionthreadlocalMap != null){
            sqlSession = sqlSessionthreadlocalMap.get(envirment);
        }
        //如果线程变量中的session为null，
        if(sqlSession==null){
            //新建session会话，并把session放在线程变量中
            newSession(envirment);
            //再次从线程变量中取session对象
            sqlSession = threadMaplocal.get().get(envirment);
        }
        return sqlSession;
    }
    /**
     * 关闭session对象，并从线程变量中删除
     */
    public synchronized static void closeSession(String envirment){
        //读取出线程变量中session对象
        SqlSession session = threadMaplocal.get().get(envirment);
        //如果session对象不为空，关闭sessoin对象，并清空线程变量
        if(session!=null){
            session.close();
            threadMaplocal.get().remove(envirment);
        }
    }


    /**
     * 新建session会话，并把session放在线程变量中
     */
    private static void newSession(boolean isAutoCommit,String envirment) {
        SqlSessionFactory sqlSessionFactory = getSessionFactory(envirment);
        // 打开一个session会话
        SqlSession session = sqlSessionFactory.openSession(isAutoCommit);
        // 将session会话保存在本线程变量中
        HashMap<String, SqlSession> sqlSessionthreadlocalMap = threadMaplocal.get();
        if(sqlSessionthreadlocalMap == null){
            sqlSessionthreadlocalMap = new HashMap<>();
        }
        sqlSessionthreadlocalMap.put(envirment,session);
        threadMaplocal.set(sqlSessionthreadlocalMap);
    }
    /**
     * 新建session会话，并把session放在线程变量中
     */
    private static void newSession(String envirment) {
        SqlSessionFactory sqlSessionFactory = getSessionFactory(envirment);
        // 打开一个session会话
        SqlSession session = sqlSessionFactory.openSession();
        // 将session会话保存在本线程变量中
        HashMap<String, SqlSession> sqlSessionthreadlocalMap = threadMaplocal.get();
        if(sqlSessionthreadlocalMap == null){
            sqlSessionthreadlocalMap = new HashMap<>();
        }
        sqlSessionthreadlocalMap.put(envirment,session);
        threadMaplocal.set(sqlSessionthreadlocalMap);
    }

}

