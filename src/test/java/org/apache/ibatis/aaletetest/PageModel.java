package org.apache.ibatis.aaletetest;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询封装类：支持按页page起始和按start起始
 * records：data记录
 * totalCount：记录总数
 * isEnd：是否结束
 * nextStartIndex：按start+limit查询时表示下一页数据的起始位置
 * nextPageIndex： 按page+pageSize查询时表示下页面的起始页码
 */
public class PageModel implements Serializable {

    private List<?> records;

    private int totalCount;

    private boolean isEnd;

    private int nextStartIndex;

    private int nextPageIndex;

    public PageModel() {
    }
    public void setEnd(boolean end) {
        isEnd = end;
    }
    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }


    public void setRecords(List<?> records) {
        this.records = records;
    }

    public List<?> getRecords() {
        return records;
    }


    public int getNextStartIndex() {
        return nextStartIndex;
    }

    public void setNextStartIndex(int nextStartIndex) {
        this.nextStartIndex = nextStartIndex;
    }


    public int getNextPageIndex() {
        return nextPageIndex;
    }

    public void setNextPageIndex(int nextPageIndex) {
        this.nextPageIndex = nextPageIndex;
    }

    public PageModel(List<?> records, int totalCount, boolean isEnd) {
        this.records = records;
        this.totalCount = totalCount;
        this.isEnd = isEnd;
    }


    public boolean isEnd() {
        return isEnd;
    }


}
