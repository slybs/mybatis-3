/**
 *    Copyright 2009-2019 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.datasource.unpooled;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;

/**
 * @author Clinton Begin
 * @author Eduardo Macarron
 */
public class UnpooledDataSource implements DataSource {

  /**
   * 已注册的 Driver 映射
   *
   * KEY：Driver 类名
   * VALUE：Driver 对象
   */
  private static Map<String, Driver> registeredDrivers = new ConcurrentHashMap<>();

  /**
   * Driver 类加载器
   */
  private ClassLoader driverClassLoader;
  /**
   * Driver 属性
   */
  private Properties driverProperties;

  /**
   * Driver 类名
   */
  private String driver;
  /**
   * 数据库 URL
   */
  private String url;
  /**
   * 数据库用户名
   */
  private String username;
  /**
   * 数据库密码
   */
  private String password;

  /**
   * 是否自动提交事务
   */
  private Boolean autoCommit;
  /**
   * 默认事务隔离级别
   */
  private Integer defaultTransactionIsolationLevel;
  private Integer defaultNetworkTimeout;

  static {
    // 初始化 registeredDrivers
    //这里，也就是说UnpooledDataSource加载时，是通过jdk中的DriverManager中
//    static {
//      loadInitialDrivers();
//      println("JDBC DriverManager initialized");
//    }
    //把实现了Driver接口的实现类（比如通过maven导入的驱动实现类）都加载进来的。
    //就是说：即使没有通过Class.forName(*)显示的手动加载驱动,在项目中的实现了Driver的实现类驱动也会被加载进来的。
//    jdbc4.0 是不用显式的去加载驱动，如果驱动包符合 SPI 模式就会自动加载
//    就是说程序会自动去项目中查找是否有驱动，当然没有驱动的话自然是连接不了的
    //JDK 1.6 - JDBC4：java1.6开始起使用JDBC4了
    Enumeration<Driver> drivers = DriverManager.getDrivers();
    while (drivers.hasMoreElements()) {
      Driver driver = drivers.nextElement();
      registeredDrivers.put(driver.getClass().getName(), driver);
    }
  }

  public UnpooledDataSource() {
  }

  public UnpooledDataSource(String driver, String url, String username, String password) {
    this.driver = driver;
    this.url = url;
    this.username = username;
    this.password = password;
  }

  public UnpooledDataSource(String driver, String url, Properties driverProperties) {
    this.driver = driver;
    this.url = url;
    this.driverProperties = driverProperties;
  }

  public UnpooledDataSource(ClassLoader driverClassLoader, String driver, String url, String username, String password) {
    this.driverClassLoader = driverClassLoader;
    this.driver = driver;
    this.url = url;
    this.username = username;
    this.password = password;
  }

  public UnpooledDataSource(ClassLoader driverClassLoader, String driver, String url, Properties driverProperties) {
    this.driverClassLoader = driverClassLoader;
    this.driver = driver;
    this.url = url;
    this.driverProperties = driverProperties;
  }

  @Override
  public Connection getConnection() throws SQLException {
    return doGetConnection(username, password);
  }

  @Override
  public Connection getConnection(String username, String password) throws SQLException {
    return doGetConnection(username, password);
  }

  @Override
  public void setLoginTimeout(int loginTimeout) {
    DriverManager.setLoginTimeout(loginTimeout);
  }

  @Override
  public int getLoginTimeout() {
    return DriverManager.getLoginTimeout();
  }

  @Override
  public void setLogWriter(PrintWriter logWriter) {
    DriverManager.setLogWriter(logWriter);
  }

  @Override
  public PrintWriter getLogWriter() {
    return DriverManager.getLogWriter();
  }

  public ClassLoader getDriverClassLoader() {
    return driverClassLoader;
  }

  public void setDriverClassLoader(ClassLoader driverClassLoader) {
    this.driverClassLoader = driverClassLoader;
  }

  public Properties getDriverProperties() {
    return driverProperties;
  }

  public void setDriverProperties(Properties driverProperties) {
    this.driverProperties = driverProperties;
  }

  public synchronized String getDriver() {
    return driver;
  }

  public synchronized void setDriver(String driver) {
    this.driver = driver;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Boolean isAutoCommit() {
    return autoCommit;
  }

  public void setAutoCommit(Boolean autoCommit) {
    this.autoCommit = autoCommit;
  }

  public Integer getDefaultTransactionIsolationLevel() {
    return defaultTransactionIsolationLevel;
  }

  public void setDefaultTransactionIsolationLevel(Integer defaultTransactionIsolationLevel) {
    this.defaultTransactionIsolationLevel = defaultTransactionIsolationLevel;
  }

  /**
   * @since 3.5.2
   */
  public Integer getDefaultNetworkTimeout() {
    return defaultNetworkTimeout;
  }

  /**
   * Sets the default network timeout value to wait for the database operation to complete. See {@link Connection#setNetworkTimeout(java.util.concurrent.Executor, int)}
   *
   * @param defaultNetworkTimeout
   *          The time in milliseconds to wait for the database operation to complete.
   * @since 3.5.2
   */
  public void setDefaultNetworkTimeout(Integer defaultNetworkTimeout) {
    this.defaultNetworkTimeout = defaultNetworkTimeout;
  }

  private Connection doGetConnection(String username, String password) throws SQLException {
    Properties props = new Properties();
    if (driverProperties != null) {
      props.putAll(driverProperties);
    }
    if (username != null) {
      props.setProperty("user", username);
    }
    if (password != null) {
      props.setProperty("password", password);
    }
    // 执行获得 Connection 连接
    return doGetConnection(props);
  }

  private Connection doGetConnection(Properties properties) throws SQLException {
    // <1> 初始化 Driver
    initializeDriver();
    // <2> 获得 Connection 对象
    Connection connection = DriverManager.getConnection(url, properties);
    // <3> 配置 Connection 对象
    configureConnection(connection);
    return connection;
  }

  /**
   * 初始化 Driver
   * @throws SQLException
   * 如上，initializeDriver 方法主要包含三步操作，分别如下：
   *
   * 加载驱动
   * 通过反射创建驱动实例
   * 注册驱动实例
   * 这三步都是都是常规操作，比较容易理解。上面代码中出现了缓存相关的逻辑，这个是用于避免重复注册驱动。
   * 因为 initializeDriver 放阿飞并不是在 UnpooledDataSource 初始化时被调用的，而是在获取数据库连接时被调用的。
   * 因此这里需要做个检测，避免每次获取数据库连接时都重新注册驱动。
   * 这个是一个比较小的点，大家看代码时注意一下即可。下面看一下获取数据库连接的逻辑。
   */
  private synchronized void initializeDriver() throws SQLException {
    // 判断 registeredDrivers 是否已经存在该 driver ，若不存在，进行初始化
    // 检测缓存中是否包含了与 driver 对应的驱动实例
    if (!registeredDrivers.containsKey(driver)) {
      Class<?> driverType;
      try {
        // <2> 获得 driver 类
        // 使用 driverClassLoader 加载驱动
        if (driverClassLoader != null) {
          driverType = Class.forName(driver, true, driverClassLoader);
        } else {
          // 通过其他 ClassLoader 加载驱动
          driverType = Resources.classForName(driver);
        }
        // DriverManager requires the driver to be loaded via the system ClassLoader.
        // http://www.kfu.com/~nsayer/Java/dyn-jdbc.html
        // <3> // 通过反射创建驱动实例 创建 Driver 对象
        Driver driverInstance = (Driver)driverType.getDeclaredConstructor().newInstance();
        // 创建 DriverProxy 对象，并注册到 DriverManager 中
        /*
         * 注册驱动，注意这里是将 Driver 代理类 DriverProxy 对象注册到 DriverManager 中的，
         * 而非 Driver 对象本身。DriverProxy 中并没什么特别的逻辑，就不分析。
         */
        DriverManager.registerDriver(new DriverProxy(driverInstance));
        // 缓存驱动类名和实例
        // 添加到 registeredDrivers 中
        registeredDrivers.put(driver, driverInstance);
      } catch (Exception e) {
        throw new SQLException("Error setting driver on UnpooledDataSource. Cause: " + e);
      }
    }
  }

  /**
   * 配置 Connection 对象
   * @param conn
   * @throws SQLException
   */
  private void configureConnection(Connection conn) throws SQLException {
    if (defaultNetworkTimeout != null) {
      conn.setNetworkTimeout(Executors.newSingleThreadExecutor(), defaultNetworkTimeout);
    }
    // 设置自动提交
    if (autoCommit != null && autoCommit != conn.getAutoCommit()) {
      conn.setAutoCommit(autoCommit);
    }
    // 设置事务隔离级别
    if (defaultTransactionIsolationLevel != null) {
      conn.setTransactionIsolation(defaultTransactionIsolationLevel);
    }
  }

  private static class DriverProxy implements Driver {
    private Driver driver;

    DriverProxy(Driver d) {
      this.driver = d;
    }

    @Override
    public boolean acceptsURL(String u) throws SQLException {
      return this.driver.acceptsURL(u);
    }

    @Override
    public Connection connect(String u, Properties p) throws SQLException {
      return this.driver.connect(u, p);
    }

    @Override
    public int getMajorVersion() {
      return this.driver.getMajorVersion();
    }

    @Override
    public int getMinorVersion() {
      return this.driver.getMinorVersion();
    }

    @Override
    public DriverPropertyInfo[] getPropertyInfo(String u, Properties p) throws SQLException {
      return this.driver.getPropertyInfo(u, p);
    }

    @Override
    public boolean jdbcCompliant() {
      return this.driver.jdbcCompliant();
    }
    //使用 MyBatis 自定义的 Logger 对象。
    @Override
    public Logger getParentLogger() {
      return Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    }
  }

  @Override
  public <T> T unwrap(Class<T> iface) throws SQLException {
    throw new SQLException(getClass().getName() + " is not a wrapper.");
  }

  @Override
  public boolean isWrapperFor(Class<?> iface) throws SQLException {
    return false;
  }
  @Override
  public Logger getParentLogger() {
    // requires JDK version 1.6
    return Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
  }

}
