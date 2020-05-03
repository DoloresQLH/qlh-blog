package edu.uyuyue.dyblog.dao;

import edu.uyuyue.dyblog.entity.BlogCategory;
import edu.uyuyue.dyblog.entity.BlogTag;
import edu.uyuyue.dyblog.entity.BlogTagCount;
import edu.uyuyue.dyblog.util.PageQueryUtil;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BlogTagMapper {
    int deleteByPrimaryKey(Integer tagId);

    int insert(BlogTag record);

    int insertSelective(BlogTag record);

    BlogTag selectByPrimaryKey(Integer tagId);

    BlogTag selectByTagName(String tagName);

    int updateByPrimaryKeySelective(BlogTag record);

    int updateByPrimaryKey(BlogTag record);

    List<BlogTag> findTagList(PageQueryUtil pageUtil);

    int getTotalTags(PageQueryUtil pageUtil);

    int deleteBatch(Integer[] ids);

    int batchInsertBlogTag(List<BlogTag> tagList);

    List<BlogTagCount> getTagCount();
}