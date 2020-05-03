package edu.uyuyue.dyblog.service.impl;

import edu.uyuyue.dyblog.dao.AdminUserMapper;
import edu.uyuyue.dyblog.entity.AdminUser;
import edu.uyuyue.dyblog.service.AdminUserService;
import edu.uyuyue.dyblog.util.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by DuanYuan on 2019-09-28 14:33:22
 * Copyright © 2019 DuanYuan. All rights reserved.
 * 管理员与用户登陆业务
 */

@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Resource
    private AdminUserMapper adminUserMapper;

    @Override
    public AdminUser login(String userName, String password) {
        String passwordMd5 = MD5Util.MD5Encode(password,"UTF-8");
        return adminUserMapper.login(userName,passwordMd5);
    }

    @Override
    public AdminUser getUserDetailById(Integer loginUserId) {
        return adminUserMapper.selectByPrimaryKey(loginUserId);
    }

    @Override
    public boolean updatePassowrd(Integer loginUserId, String originalPassword, String newPassword) {
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(loginUserId);
        //当前用户存在时才可以删除
        if (adminUser != null){
            String originalPasswordMd5 = MD5Util.MD5Encode(originalPassword, "UTF-8");
            String newPasswordMd5 = MD5Util.MD5Encode(newPassword, "UTF-8");
            //比较原密码是否一致
            if (originalPasswordMd5.equals(adminUser.getLoginPassword())){
                //设置新密码并修改
                adminUser.setLoginPassword(newPasswordMd5);
                if (adminUserMapper.updateByPrimaryKeySelective(adminUser) > 0){
                    //修改成功返回true
                    return true;
                }
            }

        }
        return false;
    }

    @Override
    public boolean updateName(Integer loginUserId, String loginUserName, String nickName) {
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(loginUserId);
        if (adminUser != null){
            adminUser.setLoginUserName(loginUserName);
            adminUser.setNickName(nickName);
            if (adminUserMapper.updateByPrimaryKeySelective(adminUser) > 0){
                return true;
            }
        }
        return false;

    }
}
