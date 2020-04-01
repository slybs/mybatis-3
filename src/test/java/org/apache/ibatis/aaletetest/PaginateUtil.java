package org.apache.ibatis.aaletetest;

public class PaginateUtil {
    /**
     * 分页有两种方式，一种是MySQL的start,limit，一种是pageNo,pageSize，此方法提供start到pageNo的转换
     * 适用于start从0开始，limit固定的情况
     * @param start 页码
     * @param limit limit是页数量大小
     * @return 返回start记录所在页面的序号
     */
    public static int startToPageNo(int start, int limit) {
        return (start + 1) / limit + 1;
    }

    /**
     * 返回pageNo对应的页面起始的序号
     * limit是页数量大小
     * @param pageNo 页码 从1开始
     * @param limit limit是页数量大小
     * @return
     */
    public static int pageNoToStart(int pageNo, int limit){
        return (pageNo-1)*limit;
    }
}
