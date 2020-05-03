package edu.uyuyue.dyblog.util;

import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by DuanYuan on 2019-09-29 16:20:24
 * Copyright © 2019 DuanYuan. All rights reserved.
 */

public class PageQueryUtil extends LinkedHashMap<String, Object> {
    //当前页码
    private int page;
    //每页条数
    private int limit;
    //获取的列名
    private String sidx;
    //获取的排序规则
    private String sord;

    public PageQueryUtil(Map<String, Object> params) {
        this.putAll(params);

        //分页参数
        this.page = Integer.parseInt(params.get("page").toString());
        this.limit = Integer.parseInt(params.get("limit").toString());
        this.sidx =  String.valueOf(params.get("sidx"));
        this.sord = String.valueOf(params.get("order"));
        this.put("start", (page - 1) * limit);
        this.put("page", page);
        this.put("limit", limit);
        this.put("sidx", sidx);
        this.put("sord",sord);
    }


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx == null ? null : sidx.trim();
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord == null ? null : sord.trim();
    }

    @Override
    public String toString() {
        return "PageQueryUtil{" +
                "page=" + page +
                ", limit=" + limit +
                ", sidx='" + sidx + '\'' +
                ", sord='" + sord + '\'' +
                '}';
    }
}
