package edu.uyuyue.dyblog.entity;

/**
 * Created by DuanYuan on 2019-10-11 14:38:35
 * Copyright © 2019 DuanYuan. All rights reserved.
 */

public class BlogTagCount {

    private Integer tagId;

    private String tagName;

    private Integer tagCount;

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Integer getTagCount() {
        return tagCount;
    }

    public void setTagCount(Integer tagCount) {
        this.tagCount = tagCount;
    }

    @Override
    public String toString() {
        return "BlogTagCount{" +
                "tagId=" + tagId +
                ", tagName='" + tagName + '\'' +
                ", tagCount=" + tagCount +
                '}';
    }
}
