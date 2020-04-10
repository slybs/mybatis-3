package com.lege.officialcn.domain;

/**
 * @Author 了个
 * @date 2020/1/10 14:39
 */
public class MultiTest {
    //`id` INT(11) NOT NULL,
    //`keyword` VARCHAR(25) NOT NULL,
    //`url` VARCHAR(30) DEFAULT NULL,
    private int id;
    private String keyword;
    private String url;


    public MultiTest(int id, String keyword, String url) {
        this.id = id;
        this.keyword = keyword;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
