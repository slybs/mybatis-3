package com.lege.officialcn.XMLMappingSqlFile.InsertAndUpdate.useGeneratedKeysAndSelectKey;

import com.google.common.collect.Lists;
import com.lege.officialcn.XMLMappingSqlFile.SqlVo;
import com.lege.officialcn.dao.resultMapTestDao.MapperUserDao;
import com.lege.officialcn.dao.useGeneratedKeysAndSelectKeyDao.AuthorDao;
import com.lege.officialcn.domain.resultMapTestDomain.MapperUser;
import com.lege.officialcn.domain.useGeneratedKeysAndSelectKeyDomain.Author;
import org.apache.ibatis.session.SqlSession;

import java.util.*;


/**
 *
 */
public class MainTest {
    public static void main(String[] args) throws Exception {
        //insert01();
        //insert02();
        insertAuthor21();
        //insertAuthor3Batch();
        batchGet();
    }

    private static void batchGet() {
        SqlSession sqlSession = SqlSessionUtil.getSession(true);
        AuthorDao mapper = sqlSession.getMapper(AuthorDao.class);
        int[] a1=new int[5]; //初始化为默认值,int型为0
        int[] a2={1,2,3,4,5}; //初始化为给定值
        int[] a3=new int[]{1,2,3,4,5}; //同(3) int[] ...
        ArrayList<Integer> ids = Lists.newArrayList(1, 2, 3, 4, 5);
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("ids", ids);
        paramsMap.put("username", "Username002");
        List<Author> results1 = mapper.batchGetInParamList(ids);

        List<Author> results2 = mapper.batchGetInParamArray(a2);

        List<Author> results3 = mapper.batchGetInParamMap(paramsMap);

        System.out.println(results3.size());
    }

    private static void insertAuthor3Batch() {
        SqlSession sqlSession = SqlSessionUtil.getSession(true);
        AuthorDao mapper = sqlSession.getMapper(AuthorDao.class);
        List<Author> authorList = new ArrayList<>();
        Author author1 = new Author();
        author1.setEmail("Email1001");
        author1.setPassword("Password1001");
        author1.setUsername("Username1001");
        Author author = new Author();
        author.setEmail("Email1002");
        author.setPassword("Password1002");
        author.setUsername("Username1002");
        authorList.add(author);
        authorList.add(author1);
        int i = 0;
        try {
            i = mapper.insertAuthor3Batch(authorList);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(i);
        for (Author au: authorList) {
            System.out.println(au.getId());
        }

    }

    private static void insert01() {
        SqlSession sqlSession = SqlSessionUtil.getSession(true);
        AuthorDao mapper = sqlSession.getMapper(AuthorDao.class);
        Author author = new Author();
        author.setEmail("Email001");
        author.setPassword("Password001");
        author.setUsername("Username001");
        mapper.insertAuthor1(author);

    }
    private static void insert02() {
        SqlSession sqlSession = SqlSessionUtil.getSession(true);
        AuthorDao mapper = sqlSession.getMapper(AuthorDao.class);
        Author author = new Author();
        author.setEmail("Email002");
        author.setPassword("Password002");
        author.setUsername("Username002");
        int i = mapper.insertAuthor2(author);
        System.out.println(i);//
        //获取主键
        System.out.println(author.getId());

    }
    private static void insertAuthor21() {
        SqlSession sqlSession = SqlSessionUtil.getSession(true);
        AuthorDao mapper = sqlSession.getMapper(AuthorDao.class);
        Author author = new Author();
        author.setEmail("Email0021");
        author.setPassword("Password0021");
        author.setUsername("Username0021");
        int i = mapper.insertAuthor21(author);
        System.out.println(i);//
        //获取主键
        System.out.println(author.getId());

    }



}
