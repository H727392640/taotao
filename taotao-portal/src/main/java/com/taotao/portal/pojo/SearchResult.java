package com.taotao.portal.pojo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: H
 * Date: 2019/3/8
 * Time: 16:04
 * Description: No Description
 */
public class SearchResult {
    //商品列表
    private List<Item> itemList;
    //总记录数
    private long recordCount;
    //总页数
    private long pageCount;
    //当前页
    private long curPage;

    public List<Item> getItemList() {
        return itemList;
    }
    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
    public long getRecordCount() {
        return recordCount;
    }
    public void setRecordCount(long recordCount) {
        this.recordCount = recordCount;
    }
    public long getPageCount() {
        return pageCount;
    }
    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }
    public long getCurPage() {
        return curPage;
    }
    public void setCurPage(long curPage) {
        this.curPage = curPage;
    }

}
