package edu.uyuyue.dyblog.entity;

import java.util.Date;

/**
 * Created by DuanYuan on 2019-10-16 14:28:19
 * Copyright © 2019 DuanYuan. All rights reserved.
 */

public class BlogConfig {
    private String configName;

    private String configValue;

    private Date createTime;

    private Date updateTime;

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
