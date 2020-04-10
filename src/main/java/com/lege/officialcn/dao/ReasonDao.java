package com.lege.officialcn.dao;

import com.lege.officialcn.domain.Reason;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;

/**
 * @Author 了个
 * @date 2020/1/14 10:23
 */
public interface ReasonDao {
    public Reason findReasonById(@Param("ID") BigInteger id);
    public Reason findReasonWithSubCategoryNameLike(@Param("subcategoryname") String subcategoryname);
    public Reason findReasonWithSubCategoryNameLike2(@Param("subcategoryname") String subcategoryname, @Param("authorcontent") String authorcontent);
    public Reason findChooseWhenOtherwise(@Param("subcategoryname") String subcategoryname, @Param("authorcontent") String authorcontent);
    public Reason findReasonWithTrimWhereSet1(
      @Param("categoryname") String categoryname,
      @Param("subcategoryname") String subcategoryname, @Param("authorcontent") String authorcontent);
    public Reason findReasonWithTrimWhereSet2(
      @Param("categoryname") String categoryname,
      @Param("subcategoryname") String subcategoryname, @Param("authorcontent") String authorcontent);
}
