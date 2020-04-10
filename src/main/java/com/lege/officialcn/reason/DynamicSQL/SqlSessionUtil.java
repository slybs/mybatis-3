package com.lege.officialcn.reason.DynamicSQL;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
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
public class SqlSessionUtil {
    /**
     * SqlSessionFactory 一旦被创建就应该在应用的运行期间一直存在，没有任何理由丢弃它或重新创建另一个实例。
     * 使用 SqlSessionFactory 的最佳实践是在应用运行期间不要重复创建多次，多次重建 SqlSessionFactory 被视为一种代码“坏味道（bad smell）”。
     * 因此 SqlSessionFactory 的最佳作用域是应用作用域。 有很多方法可以做到，最简单的就是使用单例模式或者静态单例模式。
     */
    private static SqlSessionFactory sessionFactory = null;
    /*
     * 创建本地线程变量，为每一个线程独立管理一个session对象 每一个线程只有且仅有单独且唯一的一个session对象
     * 加上线程变量对session进行管理，可以保证线程安全，避免多实例同时调用同一个session对象
     * 每一个线程都会new一个线程变量，从而分配到自己的session对象
     */
    private static ThreadLocal<SqlSession> threadlocal = new ThreadLocal<SqlSession>();

    // 创建sessionFactory对象，因为整个应用程序只需要一个实例对象，故用静态代码块
    static {
        try {
            //Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            Reader reader = Resources.getResourceAsReader("ReasonMapper/mybatis-config.xml");
            /**
             * 这个类可以被实例化、使用和丢弃，一旦创建了 SqlSessionFactory，就不再需要它了。
             * 因此 SqlSessionFactoryBuilder 实例的最佳作用域是方法作用域（也就是局部方法变量）。
             * 你可以重用 SqlSessionFactoryBuilder 来创建多个 SqlSessionFactory 实例，但是最好还是不要让其一直存在，
             * 以保证所有的 XML 解析资源可以被释放给更重要的事情。
             */
            sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回sessionFactory对象 工厂对象
     *
     * @return sessionFactory
     */
    public static SqlSessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * 新建session会话，并把session放在线程变量中
     */
    private static void newSession(boolean isAutoCommit) {
        // 打开一个session会话
        SqlSession session = sessionFactory.openSession(isAutoCommit);
        // 将session会话保存在本线程变量中
        threadlocal.set(session);
    }
    /**
     * 新建session会话，并把session放在线程变量中
     */
    private static void newSession() {
        // 打开一个session会话
        SqlSession session = sessionFactory.openSession();
        // 将session会话保存在本线程变量中
        threadlocal.set(session);
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
    public static SqlSession getSession(boolean isAutoCommit){
        //优先从线程变量中取session对象
        SqlSession session = threadlocal.get();
        //如果线程变量中的session为null，
        if(session==null){
            //新建session会话，并把session放在线程变量中
            newSession(isAutoCommit);
            //再次从线程变量中取session对象
            session = threadlocal.get();
        }
        return session;
    }
    public static SqlSession getSession(){
        //优先从线程变量中取session对象
        SqlSession session = threadlocal.get();
        //如果线程变量中的session为null，
        if(session==null){
            //新建session会话，并把session放在线程变量中
            newSession();
            //再次从线程变量中取session对象
            session = threadlocal.get();
        }
        return session;
    }
    /**
     * 关闭session对象，并从线程变量中删除
     */
    public static void closeSession(){
        //读取出线程变量中session对象
        SqlSession session = threadlocal.get();
        //如果session对象不为空，关闭sessoin对象，并清空线程变量
        if(session!=null){
            session.close();
            threadlocal.set(null);
        }
    }

}

