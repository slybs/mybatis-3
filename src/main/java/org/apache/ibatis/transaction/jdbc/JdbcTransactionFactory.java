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
package org.apache.ibatis.transaction.jdbc;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.TransactionFactory;

/**
 * Creates {@link JdbcTransaction} instances.
 * @author Clinton Begin
 * @see JdbcTransaction
 *  事务工厂Transaction定义了创建Transaction的两个方法：
 *    一个是通过指定的Connection对象创建Transaction，
 *    另外是通过数据源DataSource来创建Transaction。
 */
public class JdbcTransactionFactory implements TransactionFactory {
  /**
   * 根据给定的数据库连接Connection创建Transaction
   * @param conn Existing database connection
   * @return
   */
  @Override
  public Transaction newTransaction(Connection conn) {
    return new JdbcTransaction(conn);
  }

  /**
   *  SqlSessionFactory#openSession()和
   *  SqlSessionFactory#openSession(authoCommit都会调用这里)
   * 根据DataSource、隔离级别和是否自动提交创建Transacion
   * @param ds
   * @param level Desired isolation level
   * @param autoCommit Desired autocommit
   * @return
   */
  @Override
  public Transaction newTransaction(DataSource ds, TransactionIsolationLevel level, boolean autoCommit) {
    return new JdbcTransaction(ds, level, autoCommit);
  }
}
