package com.zhiyou.entity;

import java.util.List;

public class PageResult<T> {
    //	Role类型的List集合
    private List<T> items;
    //	2.总条数:数据库查询得到
    private int totalCount;
    //	3.总页数：计算得来:(int)Math.ceil(total*1.0/pageSize)
    private int totalPageCount;
    //	4.当前页：参数传递
    private int pageNo;

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

}
