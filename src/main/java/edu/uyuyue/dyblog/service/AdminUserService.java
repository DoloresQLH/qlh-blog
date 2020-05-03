package edu.uyuyue.dyblog.service;

import edu.uyuyue.dyblog.entity.AdminUser;

public interface AdminUserService {

    AdminUser getUserDetailById(Integer loginUserId);


    AdminUser login(String userName, String password);

    boolean updatePassowrd(Integer loginUserId, String originalPassword, String newPassword);

    boolean updateName(Integer loginUserId, String loginUserName, String nickName);
}
