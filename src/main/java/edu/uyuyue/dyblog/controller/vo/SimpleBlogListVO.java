package edu.uyuyue.dyblog.controller.vo;

import java.io.Serializable;

/**
 * Created by DuanYuan on 2019-10-11 14:25:58
 * Copyright © 2019 DuanYuan. All rights reserved.
 */

public class SimpleBlogListVO implements Serializable {
    private Long blogId;

    private String blogTitle;

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    @Override
    public String toString() {
        return "SimpleBlogListVO{" +
                "blogId=" + blogId +
                ", blogTitle='" + blogTitle + '\'' +
                '}';
    }
}
