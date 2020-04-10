package com.lege.officialcn.domain;

import java.math.BigInteger;
import java.sql.Date;

/**
 * @Author 了个
 * @date 2020/1/14 10:23
 */
public class Reason {
    //`ID` BIGINT(20) UNSIGNED NOT NULL,
    //`business_id` TINYINT(4) NOT NULL COMMENT '',
    //`categoryid` BIGINT(20) UNSIGNED NOT NULL DEFAULT '0' COMMENT '',
    //`categoryname` VARCHAR(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '',
    //`subcategoryid` INT(10) UNSIGNED NOT NULL COMMENT '',
    //`subcategoryname` VARCHAR(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '',
    //`reply_msg` VARCHAR(1024) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '',
    //`authorcontent` VARCHAR(1000) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '',
    //`email` VARCHAR(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '',
    //`createtime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    //`updatetime` DATETIME NOT NULL COMMENT '更改时间',
    //`business_name` VARCHAR(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '业务名称',
    //`extra` MEDIUMTEXT COLLATE utf8mb4_unicode_ci COMMENT '附加信息',
    private BigInteger ID;
    private int businessId;
    private BigInteger categoryId;
    private String categoryName;
    private int subCategoryId;
    private String subCategoryName;
    private String replyMsg;
    private String authorContent;
    private String email;
    //DATETIME	DATETIME	java.sql.Timestamp
    //TIMESTAMP[(M)]	TIMESTAMP	java.sql.Timestamp
    private Date createTime;
    private Date updatetime;
    private String businessN_name;
    private String extra;

    public BigInteger getID() {
        return ID;
    }

    public void setID(BigInteger ID) {
        this.ID = ID;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public BigInteger getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(BigInteger categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(int subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getReplyMsg() {
        return replyMsg;
    }

    public void setReplyMsg(String replyMsg) {
        this.replyMsg = replyMsg;
    }

    public String getAuthorContent() {
        return authorContent;
    }

    public void setAuthorContent(String authorContent) {
        this.authorContent = authorContent;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getBusinessN_name() {
        return businessN_name;
    }

    public void setBusinessN_name(String businessN_name) {
        this.businessN_name = businessN_name;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }


    @Override
    public String toString() {
        return "Reason{" +
                "ID=" + ID +
                ", businessId=" + businessId +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", subCategoryId=" + subCategoryId +
                ", subCategoryName='" + subCategoryName + '\'' +
                ", replyMsg='" + replyMsg + '\'' +
                ", authorContent='" + authorContent + '\'' +
                ", email='" + email + '\'' +
                ", createTime=" + createTime +
                ", updatetime=" + updatetime +
                ", businessN_name='" + businessN_name + '\'' +
                ", extra='" + extra + '\'' +
                '}';
    }
}
