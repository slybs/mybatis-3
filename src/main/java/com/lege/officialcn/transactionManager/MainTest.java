package com.lege.officialcn.transactionManager;

import com.lege.utils.MybatisUtil01;
import com.lege.officialcn.SqlSessionUtil.MultiDataSourceSqlSessionUtil;
import com.lege.officialcn.dao.ITopicDao;
import com.lege.officialcn.dao.IUserDao;
import com.lege.officialcn.domain.TopicEntity;
import com.lege.officialcn.domain.User;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;
import java.util.List;

/**大总结：
 *      1.mybatis默认自动提交是ture；
 *      2.如果在不设置默认提交为false的情况下，所有会话内的操作，不会回滚，无论是同库同表还是同库不同表
 *      3.如果在设置了自动提交为false的话，然后异常回滚操作的话，同库的情况下失败前的操作会回滚
 * @Author 了个
 * @date 2020/1/7 17:29
 * =============>:
 * 小结一下：前提是都正常提交，sqlSession.rollback();
 *      1.如果autoCommit = false，那么对同一个表的多次 update insert 操作，在后面出错时，前面的操作会回滚，不会出现部分成功----test1
 *          sqlSession.rollback();
 *      2.如果autoCommit = true，那么对同一个表的多次 update insert  操作，在后面出错时，前面的操作不会回滚，会部分成功----test2
 * =============>:也就是说，isAutoCommit=true时，都会出现部分成功，无论有没有在异常时进行sqlSession.rollback()
 *      3.autocommit = false, sqlSession.rollback(); 时：同步下的不同表的update insert操作，在后面操作失败时，会回滚
 *      4.TODO 那么问题是：autocommit = false, sqlSession.rollback(); 时：
 *          不同库表的操作，在后面失败时，前面操作会回滚吗。不会，为什么，因为不同的SqlSession，为什么，因为mybatis的多数据源下，不同数据源需要不同的
 *          SqlSessionFactory，创建的SqlSession，不在同一域下
 * =============>:小结：不同数据源，不同会话（SqlSession），必然不能回滚！,是因为
 */
public class MainTest {
    /**
     * 这样在DefaultSqlSessionFactory中创建SqlSession时，可以设置数据库的事务隔离级别，以及通过设置autoCommit来设置事务的提交方式，方式如下：
     * 当autoCommit = false时，插入的数据报错时，修改的内容不会提交到数据库，但是如果没有进行rollback回滚操作时就会造成数据库死锁问题 TODO 怎么理解
     * 当autoCommit = true 对连续的操作数据中间出现错误时会部分提交，导致产生脏数据。
     *  效果展示区别在于test1和test2操作是否会回滚
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //testSingleDataSource();
        //testMultiDataSource();
//        SqlSession sqlSession = MultiDataSourceSqlSessionUtil.getSession(true,"DBServer02");
        //SqlSession sqlSession = MultiDataSourceSqlSessionUtil.getSession(false,"DBServer02");
//        test3();
//        test4();
        //test5();
    }
    /**
     * 同库不同表事务是支持的：前提一定要设置isAutoCommit=false，捕获异常时间RollBack
     */
    private static void test6() {
        SqlSession sqlSession = SqlSessionUtil.getSession(false);
        try {

            ITopicDao iTopicDao = sqlSession.getMapper(ITopicDao.class);

            TopicEntity topicEntity = new TopicEntity();
            topicEntity.setTopicId(125);
            topicEntity.setTopicType(23);
            topicEntity.setRecommend(1);
            topicEntity.setAddTime(new Date());
            topicEntity.setModifyTime(new Date());
            topicEntity.setPicUrl("ttttttt");
            topicEntity.setSource(1);
            topicEntity.setStatus(1);
            topicEntity.setStatusCode(1234);
            topicEntity.setTitle("this is a title");
            topicEntity.setUpdateTime(new Date());
            topicEntity.setSummary("cant be null");
            //这里会成功，但是由于地下出错所以会回滚
            iTopicDao.addTopic(topicEntity);




            IUserDao mapper = sqlSession.getMapper(IUserDao.class);

            List<User> list = mapper.getList();
            System.out.println("111111=" + list.size());
            User user = new User();
            user.setId(13);
            user.setAddress("21231");
            user.setBirthday(new Date());
            user.setSex("男");
            user.setUsername("用户名");
            ////这里会成功，但是由于地下出错所以会回滚
            mapper.addUser(user);


            user.setId(7);
            ////这一步会失败。问题是iTopicDao.addTopic 插入的在别的表中的户数不会回滚，会成功
            mapper.addUser(user);


            List<User> list1 = mapper.getList();

            System.out.println("22222=" + list1.size());
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            SqlSessionUtil.closeSession();
        }
    }
    /**
     * 同库不同表事务是支持的：前提一定要设置isAutoCommit=false，捕获异常时间RollBack
     */
    private static void test5() {
        SqlSession sqlSession = SqlSessionUtil.getSession(false);
        try {

            ITopicDao iTopicDao = sqlSession.getMapper(ITopicDao.class);

            TopicEntity topicEntity = new TopicEntity();
            topicEntity.setTopicId(125);
            topicEntity.setTopicType(23);
            topicEntity.setRecommend(1);
            topicEntity.setAddTime(new Date());
            topicEntity.setModifyTime(new Date());
            topicEntity.setPicUrl("ttttttt");
            topicEntity.setSource(1);
            topicEntity.setStatus(1);
            topicEntity.setStatusCode(1234);
            topicEntity.setTitle("this is a title");
            topicEntity.setUpdateTime(new Date());
            topicEntity.setSummary("cant be null");
            //这里会成功，但是由于地下出错所以会回滚
            iTopicDao.addTopic(topicEntity);




            IUserDao mapper = sqlSession.getMapper(IUserDao.class);

            List<User> list = mapper.getList();
            System.out.println("111111=" + list.size());
            User user = new User();
            user.setId(13);
            user.setAddress("21231");
            user.setBirthday(new Date());
            user.setSex("男");
            user.setUsername("用户名");
            ////这里会成功，但是由于地下出错所以会回滚
            mapper.addUser(user);


            user.setId(7);
            ////这一步会失败。问题是iTopicDao.addTopic 插入的在别的表中的户数不会回滚，会成功
            mapper.addUser(user);


            List<User> list1 = mapper.getList();

            System.out.println("22222=" + list1.size());
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            SqlSessionUtil.closeSession();
        }
    }
    private static void test4() {
        SqlSession sqlSession = SqlSessionUtil.getSession(false);
        try {
            IUserDao mapper = sqlSession.getMapper(IUserDao.class);

            List<User> list = mapper.getList();
            System.out.println("111111=" + list.size());
            User user = new User();
            user.setId(9);
            user.setAddress("21231");
            user.setBirthday(new Date());
            user.setSex("男");
            user.setUsername("用户名");
            //这一步会成功
            mapper.addUser(user);


            user.setId(10);
            mapper.addUser(user);
            List<User> list1 = mapper.getList();

            System.out.println("22222=" + list1.size());
            sqlSession.commit();
        } catch (Exception e) {
            System.out.println(e);
            //不进行回滚，会造成数据库死锁问题？TODO 怎么理解
            //sqlSession.rollback();
        }
    }
    private static void test3() {
        SqlSession sqlSession = SqlSessionUtil.getSession(false);
        try {
            IUserDao mapper = sqlSession.getMapper(IUserDao.class);

            List<User> list = mapper.getList();
            System.out.println("111111=" + list.size());
            User user = new User();
            user.setId(8);
            user.setAddress("21231");
            user.setBirthday(new Date());
            user.setSex("男");
            user.setUsername("用户名");
            //这一步会成功
            mapper.addUser(user);


            user.setId(7);
            mapper.addUser(user);
            List<User> list1 = mapper.getList();

            System.out.println("22222=" + list1.size());
        } catch (Exception e) {
            System.out.println(e);
            //不进行回滚，会造成数据库死锁问题？TODO 怎么理解
            //sqlSession.rollback();
        }
    }

    /**
     * 同库同表事务是支持的
     */
    private static void test2() {
        SqlSession sqlSession = SqlSessionUtil.getSession(true);
        try {
            IUserDao mapper = sqlSession.getMapper(IUserDao.class);

            List<User> list = mapper.getList();
            System.out.println("111111=" + list.size());
            User user = new User();
            user.setId(12);
            user.setAddress("21231");
            user.setBirthday(new Date());
            user.setSex("男");
            user.setUsername("用户名");
            //这一步会成功，即使下面的失败，因为isAuttoCommit是true，所以update操作就自动提交了
            mapper.addUser(user);


            user.setId(6);
            mapper.addUser(user);
            List<User> list1 = mapper.getList();

            System.out.println("22222=" + list1.size());
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        }
    }

    private static void test1() {
        SqlSession sqlSession = SqlSessionUtil.getSession(false);
        try {
            IUserDao mapper = sqlSession.getMapper(IUserDao.class);

            List<User> list = mapper.getList();
            System.out.println("111111=" + list.size());
            User user = new User();
            user.setId(7);
            user.setAddress("21231");
            user.setBirthday(new Date());
            user.setSex("男");
            user.setUsername("用户名");
            //这一步操作成功，但是后面插入6的时候报错，所以这里会回滚，因为没有自动提交
            mapper.addUser(user);


            user.setId(6);
            mapper.addUser(user);
            List<User> list1 = mapper.getList();

            System.out.println("22222=" + list1.size());
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        }
    }


}
