package edu.uyuyue.dyblog.service;

import edu.uyuyue.dyblog.entity.BlogComment;
import edu.uyuyue.dyblog.util.PageQueryUtil;
import edu.uyuyue.dyblog.util.PageResult;

/**
 * Created by DuanYuan on 2019-10-15 15:39:09
 * Copyright © 2019 DuanYuan. All rights reserved.
 */

public interface CommentService {
    PageResult getCommentsPage(PageQueryUtil pageUtil);

    boolean checkDone(Integer[] ids);

    boolean reply(Long commentId, String replyBody);

    boolean deleteBatch(Integer[] ids);

    boolean addComment(BlogComment comment);

    PageResult getCommentsPageByBlogIdAndPageNum(Long blogId, Integer commentPage);

    int getTotalComments();
}
