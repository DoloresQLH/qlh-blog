package edu.uyuyue.dyblog.dao;

import edu.uyuyue.dyblog.entity.BlogLink;
import edu.uyuyue.dyblog.util.PageQueryUtil;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by DuanYuan on 2019-10-08 13:58:54
 * Copyright © 2019 DuanYuan. All rights reserved.
 */

@Component
public interface BlogLinkMapper {
    List<BlogLink> findLinkList(PageQueryUtil pageUtil);

    int getTotalLinks(PageQueryUtil pageUtil);

    int insertSelective(BlogLink link);

    BlogLink selectByPrimaryKey(Integer linkId);

    Boolean updateByPrimaryKeySelective(BlogLink tempLink);

    int deleteBatch(Integer[] ids);
}
