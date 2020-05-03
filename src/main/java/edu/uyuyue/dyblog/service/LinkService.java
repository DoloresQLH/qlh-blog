package edu.uyuyue.dyblog.service;

import edu.uyuyue.dyblog.entity.BlogLink;
import edu.uyuyue.dyblog.util.PageQueryUtil;
import edu.uyuyue.dyblog.util.PageResult;

import java.util.List;
import java.util.Map;

/**
 * Created by DuanYuan on 2019-10-08 13:56:13
 * Copyright © 2019 DuanYuan. All rights reserved.
 */

public interface LinkService {

    PageResult getBlogLinkPage(PageQueryUtil pageUtil);

    Boolean saveLink(BlogLink link);

    BlogLink selectById(Integer linkId);

    Boolean updateLink(BlogLink tempLink);

    boolean deleteBatch(Integer[] ids);

    Map<Byte, List<BlogLink>> getLinksForLinkPage();

    int getTotalLinks();
}
