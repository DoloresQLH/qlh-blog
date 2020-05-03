package edu.uyuyue.dyblog.dao;

import edu.uyuyue.dyblog.entity.BlogConfig;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by DuanYuan on 2019-10-16 14:26:44
 * Copyright © 2019 DuanYuan. All rights reserved.
 */

@Component
public interface BlogConfigMapper {
    List<BlogConfig> selectAll();
}
