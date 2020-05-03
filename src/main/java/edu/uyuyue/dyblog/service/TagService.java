package edu.uyuyue.dyblog.service;

import edu.uyuyue.dyblog.entity.BlogTagCount;
import edu.uyuyue.dyblog.util.PageQueryUtil;
import edu.uyuyue.dyblog.util.PageResult;

import java.util.List;

public interface TagService {

    PageResult getBlogTagPage(PageQueryUtil pageUtil);

    boolean saveTag(String tagName);

    boolean deleteBatch(Integer[] ids);

    List<BlogTagCount> getBlogTagCountForIndex();

    int getTotalTags();
}
