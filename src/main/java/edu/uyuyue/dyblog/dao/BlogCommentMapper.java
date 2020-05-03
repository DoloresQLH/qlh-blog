package edu.uyuyue.dyblog.dao;

import edu.uyuyue.dyblog.entity.BlogComment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by DuanYuan on 2019-10-15 15:45:18
 * Copyright © 2019 DuanYuan. All rights reserved.
 */

@Component
public interface BlogCommentMapper {
    int deleteByPrimaryKey(Long commentId);

    int insert(BlogComment record);

    int insertSelective(BlogComment record);

    BlogComment selectByPrimaryKey(Long commentId);

    int updateByPrimaryKeySelective(BlogComment record);

    int updateByPrimaryKey(BlogComment record);

    List<BlogComment> findBlogCommentList(Map map);

    int getTotalBlogComments(Map map);

    int checkDone(Integer[] ids);

    int deleteBatch(Integer[] ids);
}
