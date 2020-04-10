package com.lege.officialcn.XMLMappingSqlFile;

import com.lege.officialcn.dao.ITopicDao;
import com.lege.officialcn.dao.IUserDao;
import com.lege.officialcn.dao.MultiTestDao;
import com.lege.officialcn.domain.MultiTest;
import com.lege.officialcn.domain.TopicEntity;
import com.lege.officialcn.domain.User;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @Author 了个
 * @date 2020/1/7 17:29
 */
public class MainTest {
    public static void main(String[] args) throws Exception{
        //test001();
        //test002();
        test003();
    }

    private static void test003() {
        SqlSession sqlSession1 = SqlSessionUtil.getSession(true);
        MultiTestDao testDao = sqlSession1.getMapper(MultiTestDao.class);
        for(int i=1;i<=10;i++){
            MultiTest multiTest = new MultiTest(i,"keyword-"+i,"url=>"+i);
            long start = System.currentTimeMillis();
            testDao.insert(multiTest);
            long end = System.currentTimeMillis();
            System.out.println("time="+(end-start));
        }

        SqlSessionUtil.closeSession();
    }

    private static void test002() {

        SqlSession sqlSession1 = SqlSessionUtil.getSession(true);
        ITopicDao topicDao = sqlSession1.getMapper(ITopicDao.class);
        for(int i=1;i<=10;i++){
            long start = System.currentTimeMillis();
            TopicEntity topicEntityById = topicDao.getTopicEntityById(1);
            System.out.println(topicEntityById.getTitle());
            long end = System.currentTimeMillis();
            System.out.println("time="+(end-start));
        }


        SqlSessionUtil.closeSession();
    }

    private static void test001() {

        SqlSession sqlSession = SqlSessionUtil.getSession(false);
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        List<User> all = mapper.findAll();
        System.out.println(all.size());
        System.out.println(all);
        sqlSession.commit();
        SqlSessionUtil.closeSession();

    }



}
