package edu.uyuyue.dyblog.dao;

import edu.uyuyue.dyblog.entity.AdminUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface AdminUserMapper {
//    int insert(AdminUser record);
    //验证登陆
    AdminUser login(@Param("userName") String userName, @Param("password") String password);
    //利用id搜索用户
    AdminUser selectByPrimaryKey(Integer loginUserId);

    int updateByPrimaryKeySelective(AdminUser adminUser);
    //修改用户信息

}
