package com.lege.officialcn.domain;

import java.io.Serializable;
import java.util.Date;

public class TopicEntity implements Serializable {
    private static final long serialVersionUID = -1078526269367891953L;
    private int topicId;
    private String title;
    private String summary;
    private String picUrl;
    private int topicType;
    private int recommend;
    private int status;
    private int statusCode;
    private int source;
    private Date addTime;
    private Date modifyTime;
    private Date updateTime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public int getTopicType() {
        return topicType;
    }

    public void setTopicType(int topicType) {
        this.topicType = topicType;
    }

    public int getRecommend() {
        return recommend;
    }

    public void setRecommend(int recommend) {
        this.recommend = recommend;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "TopicEntity{" +
                "topicId=" + topicId +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", topicType=" + topicType +
                ", recommend=" + recommend +
                ", status=" + status +
                ", statusCode=" + statusCode +
                ", source=" + source +
                ", addTime=" + addTime +
                ", modifyTime=" + modifyTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
