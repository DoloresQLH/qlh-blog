package edu.uyuyue.dyblog.service;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by DuanYuan on 2019-10-16 13:59:19
 * Copyright © 2019 DuanYuan. All rights reserved.
 */

public interface ConfigService {
    Map<String,String> getAllConfigs();
}
