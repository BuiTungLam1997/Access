package com.example.access.paging;

import com.example.access.sort.Sorter;

public class PageRequest implements Pageble {
    private Integer page;
    private Integer maxPageItem;
    private Sorter sorter;
    private Integer offSet = 0;
    private Integer limit = 3;

    public Sorter getSorter() {
        if (this.sorter != null) {
            return this.sorter;
        }
        return null;
    }

    public PageRequest(Integer page, Integer maxPageItem, Sorter sorter) {
        this.page = page;
        this.maxPageItem = maxPageItem;
        this.sorter = sorter;
    }

    public PageRequest(Integer offSet, Integer limit) {
        this.offSet = offSet;
        this.limit = limit;
    }
    public PageRequest() {

    }
    @Override
    public Integer getPage() {
        return this.page;
    }

    @Override
    public Integer getOffset() {
//        if (this.page != null && this.maxPageItem != null) {
//            return (this.page - 1) * this.maxPageItem;
//        } else return null;
        return offSet;
    }

    @Override
    public Integer getLimit() {
        return limit;
        //this.maxPageItem;
    }
}
