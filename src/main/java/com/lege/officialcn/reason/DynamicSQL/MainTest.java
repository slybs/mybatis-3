package com.lege.officialcn.reason.DynamicSQL;


import com.lege.officialcn.dao.ReasonDao;
import com.lege.officialcn.domain.Reason;
import org.apache.ibatis.session.SqlSession;

import java.math.BigInteger;

/**
 *
 */
public class MainTest {
    public static void main(String[] args) throws Exception {
        //testinit();
        //动态 SQL:if
        testDynamicSQLif();
    }

    private static void testDynamicSQLif() {
        SqlSession sqlSession = SqlSessionUtil.getSession(true);
        ReasonDao reasonDao = sqlSession.getMapper(ReasonDao.class);
        Reason reason = reasonDao.findReasonWithSubCategoryNameLike("111");
        System.out.println(reason);
        Reason reason2 = reasonDao.findReasonWithSubCategoryNameLike2("111", "111");
        System.out.println(reason2);
        //SELECT * FROM reason WHERE categoryname = "1111" AND subcategoryname like ?
        //Reason reason3 = reasonDao.findChooseWhenOtherwise("111", "111");
        //SELECT * FROM reason WHERE categoryname = "1111" AND authorcontent like ?
        //Reason reason3 = reasonDao.findChooseWhenOtherwise(null, "111");
        //SELECT * FROM reason WHERE categoryname = "1111" AND categoryid = 1
        Reason reason3 = reasonDao.findChooseWhenOtherwise(null, null);
        System.out.println(reason3);
        //Reason reason4 = reasonDao.findReasonWithTrimWhereSet1(null,"111", "111");
        //System.out.println(reason4);
        //SELECT * FROM reason WHERE subcategoryname like ? AND authorcontent like ?
        //Reason reason5 = reasonDao.findReasonWithTrimWhereSet2(null,"111", "111");
        //SELECT * FROM reason WHERE authorcontent like ?
        //Reason reason5 = reasonDao.findReasonWithTrimWhereSet2(null,null, "111");
        //SELECT * FROM reason WHERE categoryname like ? AND authorcontent like ?
        //Reason reason5 = reasonDao.findReasonWithTrimWhereSet2("1111",null, "111");
        //SELECT * FROM reason
        Reason reason5 = reasonDao.findReasonWithTrimWhereSet2(null,null, null);
        System.out.println(reason5);
    }

    private static void testinit() {
        SqlSession sqlSession = SqlSessionUtil.getSession(true);
        ReasonDao reasonDao = sqlSession.getMapper(ReasonDao.class);
        Reason reason = reasonDao.findReasonById(new BigInteger("1"));
        System.out.println(reason);
    }


}
