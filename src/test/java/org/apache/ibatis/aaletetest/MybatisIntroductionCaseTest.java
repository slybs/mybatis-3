package org.apache.ibatis.aaletetest;


import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.ibatis.aaletetest.dao.ITopicDao;
import org.apache.ibatis.aaletetest.dao.IUserDao;
import org.apache.ibatis.aaletetest.domain.TopicEntity;
import org.apache.ibatis.aaletetest.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisIntroductionCaseTest {

  public static void main(String[] args) throws Exception {
    test01();
    //MyBatisUtil
//        test02();
//        updateTopic();
  }

  /**
   * 00.原生MyBatis测试查询操作
   *
   * @throws Exception
   */
  private static void test01() throws Exception {
    //1.读取配置文件：E:\tyy\javaprogram\bangcomMybatis\MybatisBasic\target\classes\SqlMapConfig.xml
    InputStream in = Resources.getResourceAsStream("org/apache/ibatis/aaletetest/SqlMapConfig.xml");
    //E:\tyy\javaprogram\bangcomMybatis\MybatisBasic\target\classes\mapper\IUserDao.xml
    //是到E:\tyy\javaprogram\bangcomMybatis\MybatisBasic\target\classes\目录下找不到：java.io.IOException: Could not find resource IUserDao.xml
    //InputStream in2= Resources.getResourceAsStream("IUserDao.xml");
    String ss = "";
    //2.创建SqlSessionFactory工厂
    SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
    SqlSessionFactory sqlSessionFactory = builder.build(in);
//
//      new Thread(){
//        @Override
//        public void run(){
//          SqlSession session = sqlSessionFactory.openSession();
//          IUserDao userDao = session.getMapper(IUserDao.class);
//          for(int i=1000;i<=100000;i++){
//            User user = new User();
//            user.setId(i);
//            user.setUsername("name_"+i);
//            user.setAddress("address");
//            user.setBirthday(new Date());
//            user.setSex("nv");
//            System.out.println(userDao.addUser(user));
//            session.commit();
//          }
//        }
//      }.start();

    //3.使用工厂生产SqlSession对象
    SqlSession session = sqlSessionFactory.openSession();
    //4.使用SqlSession创建Dao接口的代理对象
    IUserDao userDao = session.getMapper(IUserDao.class);
    System.out.println(userDao.toString());
    //5.使用代理对象执行方法
    User user = userDao.findByName("name_45368");
    System.out.println(user.toString());
    //6.释放资源
    session.close();
    in.close();
  }

  /**
   * 01.使用MyBatisUtils测试查询操作
   *
   * @throws Exception
   */
  private static void test02() throws Exception {
    //SqlSession sqlSession = MyBatisUtil.getSession(true);
    SqlSession sqlSession = MybatisUtil01.getSqlSession(true);
    //4.使用SqlSession创建Dao接口的代理对象
    IUserDao userDao = sqlSession.getMapper(IUserDao.class);
    //5.使用代理对象执行方法
    List<User> users = userDao.findAll();
    for (User user : users) {
      System.out.println(user);
    }
    //6.释放资源
    sqlSession.close();
  }


  /**
   * 02.增加 success!
   */
  private static void insertTopicEntityTest() throws Exception {
    //SqlSession sqlSession = MyBatisUtil.getSession(true);
    SqlSession sqlSession = MybatisUtil01.getSqlSession(true);
    //4.使用SqlSession创建Dao接口的代理对象
    ITopicDao iTopicDao = sqlSession.getMapper(ITopicDao.class);


    TopicEntity topicEntity = new TopicEntity();
    topicEntity.setTopicId(12);
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
    //5.使用代理对象执行方法
    iTopicDao.addTopic(topicEntity);

    //6.释放资源
    sqlSession.close();
  }

  /**
   * 03.批量查询 getTopicByIds
   */
  private static void getTopicByIds() throws Exception {
    //SqlSession sqlSession = MyBatisUtil.getSession(true);
    SqlSession sqlSession = MybatisUtil01.getSqlSession(true);
    //4.使用SqlSession创建Dao接口的代理对象
    ITopicDao iTopicDao = sqlSession.getMapper(ITopicDao.class);


    List<Integer> list = Lists.newArrayList(1, 2, 12, 15);
    //5.使用代理对象执行方法
    List<TopicEntity> topicEntities = iTopicDao.getTopicByIds(list);
    System.out.println(JSON.toJSONString(topicEntities));
    //6.释放资源
    sqlSession.close();
  }

  /**
   * 04.修改 updateTopic
   */
  private static void updateTopic() throws Exception {
    //SqlSession sqlSession = MyBatisUtil.getSession(true);
    SqlSession sqlSession = MybatisUtil01.getSqlSession(true);
    //4.使用SqlSession创建Dao接口的代理对象
    ITopicDao iTopicDao = sqlSession.getMapper(ITopicDao.class);


    //5.使用代理对象执行方法

    TopicEntity topicEntity = new TopicEntity();
    topicEntity.setTopicId(12);
    topicEntity.setTopicType(23);
    topicEntity.setRecommend(1);
    topicEntity.setAddTime(new Date());
    topicEntity.setModifyTime(new Date());
    topicEntity.setPicUrl("ttttttt");
    topicEntity.setSource(1);
    topicEntity.setStatus(1);
    topicEntity.setStatusCode(1234556);
    topicEntity.setTitle("this is a title update2");
    topicEntity.setUpdateTime(new Date());
    topicEntity.setSummary("cant be null update2");
    topicEntity.setUpdateTime(new Date());

    boolean result = iTopicDao.updateTopic(topicEntity);
    System.out.println(result);//true
    //6.释放资源
    sqlSession.close();
  }

  /**
   * 05 查询多条件1
   *
   * @throws Exception
   */
  private static void getTopicIds() throws Exception {
    //SqlSession sqlSession = MyBatisUtil.getSession(true);
    SqlSession sqlSession = MybatisUtil01.getSqlSession(true);
    //4.使用SqlSession创建Dao接口的代理对象
    ITopicDao iTopicDao = sqlSession.getMapper(ITopicDao.class);


    //5.使用代理对象执行方法


    List<Integer> resultList = iTopicDao.getTopicIds(1, 100);
    System.out.println(resultList);//[1, 12]
    //6.释放资源
    sqlSession.close();
  }

  /**
   * 06 查询多条件2
   *
   * @throws Exception
   */
  private static void getSummaryByIdsAndTopicTypeAndRecommedAndStatus() throws Exception {
    //SqlSession sqlSession = MyBatisUtil.getSession(true);
    SqlSession sqlSession = MybatisUtil01.getSqlSession(true);
    //4.使用SqlSession创建Dao接口的代理对象
    ITopicDao iTopicDao = sqlSession.getMapper(ITopicDao.class);


    //5.使用代理对象执行方法

    List<Integer> topicIds = Lists.newArrayList(1, 2, 12, 34, 5);
    List<TopicEntity> resultList = iTopicDao.getSummaryByIdsAndTopicTypeAndRecommedAndStatus(topicIds, 1, 100, 1);
    System.out.println(resultList.size());//[1, 12]
    //6.释放资源
    sqlSession.close();
  }

  /**
   * 07 查询多条件3-分页查询
   *
   * @throws Exception
   */
  private static void paginateTopicEntitys() throws Exception {
    //SqlSession sqlSession = MyBatisUtil.getSession(true);
    SqlSession sqlSession = MybatisUtil01.getSqlSession(true);
    //4.使用SqlSession创建Dao接口的代理对象
    ITopicDao iTopicDao = sqlSession.getMapper(ITopicDao.class);


    //5.使用代理对象执行方法
    int page = 1;//表示第一页
    int pagesize = 1;//表示每页几条数据
    //转换成数据库中的start-limit中的start
    int start = PaginateUtil.pageNoToStart(page, pagesize);
    List<TopicEntity> resultList = iTopicDao.paginateTopic(2, 100, start, pagesize);
    System.out.println(resultList.size());//
    int totalCount = iTopicDao.paginateTopicCount(2, 100);
    System.out.println(totalCount);//
    //构造pageModel
    boolean isEnd = true;
    if (start + pagesize < totalCount) {
      isEnd = false;
    }
    PageModel pageModel = new PageModel(resultList, totalCount, isEnd);
    if (!isEnd) {
      pageModel.setNextPageIndex(page + 1);
    }
    System.out.println(JSON.toJSONString(pageModel));

    //6.释放资源
    sqlSession.close();
  }
//    public static void main(String[] args) throws Exception{
//        //test01();
//        //MyBatisUtil
//        //test02();
//        updateTopic();
//    }


}
