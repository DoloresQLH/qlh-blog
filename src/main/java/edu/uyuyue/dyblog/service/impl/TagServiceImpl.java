package edu.uyuyue.dyblog.service.impl;

import edu.uyuyue.dyblog.dao.BlogTagMapper;
import edu.uyuyue.dyblog.dao.BlogTagRelationMapper;
import edu.uyuyue.dyblog.entity.BlogCategory;
import edu.uyuyue.dyblog.entity.BlogTag;
import edu.uyuyue.dyblog.entity.BlogTagCount;
import edu.uyuyue.dyblog.service.TagService;
import edu.uyuyue.dyblog.util.PageQueryUtil;
import edu.uyuyue.dyblog.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by DuanYuan on 2019-09-29 19:20:57
 * Copyright © 2019 DuanYuan. All rights reserved.
 */

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private BlogTagMapper blogTagMapper;
    @Autowired
    private BlogTagRelationMapper relationMapper;

    @Override
    public PageResult getBlogTagPage(PageQueryUtil pageUtil) {
        List<BlogTag> tags = blogTagMapper.findTagList(pageUtil);
        int total = blogTagMapper.getTotalTags(pageUtil);
        PageResult pageResult = new PageResult(tags, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public boolean saveTag(String tagName) {
        BlogTag temp = blogTagMapper.selectByTagName(tagName);
        if (temp == null){
            BlogTag blogTag = new BlogTag();
            blogTag.setTagName(tagName);
            return blogTagMapper.insertSelective(blogTag) > 0;
        }
        return false;
    }

    @Override
    public boolean deleteBatch(Integer[] ids) {
        //已存在关联不可删除
        List<Long> relations = relationMapper.selectDistinctTagIds(ids);
        if (!CollectionUtils.isEmpty(relations)){
            return false;
        }
        return blogTagMapper.deleteBatch(ids) > 0;
    }

    public List<BlogTagCount> getBlogTagCountForIndex() {
        return blogTagMapper.getTagCount();
    }

    @Override
    public int getTotalTags() {
        return blogTagMapper.getTotalTags(null);
    }
}
