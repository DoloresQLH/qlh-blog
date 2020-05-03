package edu.uyuyue.dyblog.controller.admin;

import edu.uyuyue.dyblog.entity.AdminUser;
import edu.uyuyue.dyblog.service.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by DuanYuan on 2019-09-28 14:03:23
 * Copyright © 2019 DuanYuan. All rights reserved.
 * 管理员登陆
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminUserService adminUserService;
    @Resource
    private BlogService blogService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private LinkService linkService;
    @Resource
    private TagService tagService;
    @Resource
    private CommentService commentService;

    @GetMapping("/login")
    public String login(){
        return "admin/login";
    }

    @PostMapping(value = "/login")
    public String login(@RequestParam("userName") String userName,
                        @RequestParam("password") String password,
                        @RequestParam("verifyCode") String verifyCode,
                        HttpSession session){

        //判断验证码、用户名、密码是否为空，并用session返回提示
        if (StringUtils.isEmpty(verifyCode)){
            session.setAttribute("errorMsg","验证码不能为空");
            return "admin/login";
        }
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)){
            session.setAttribute("errorMsg","用户名或密码不能为空");
            return "admin/login";
        }

        //获取前台验证码并验证
        String kaptchaCode = session.getAttribute("verifyCode") + "";
        if (StringUtils.isEmpty(kaptchaCode) || !verifyCode.equals(kaptchaCode)){
            session.setAttribute("errorMsg","验证码错误");
        }

        //验证用户名和密码
        AdminUser adminUser = adminUserService.login(userName,password);
        if (adminUser != null){
            session.setAttribute("loginUser", adminUser.getNickName());
            session.setAttribute("loginUserId", adminUser.getAdminUserId());
            //设置session有效时间,为两小时
            session.setMaxInactiveInterval(60 * 60 *2);
            return "redirect:/admin/index";
        }else {
            session.setAttribute("errorMsg","登陆失败");
            return "admin/login";
        }
    }

    //默认管理员页面
    @GetMapping({"", "/", "/index", "/index.html"})
    public String index(HttpServletRequest request) {
        request.setAttribute("path", "index");
        request.setAttribute("categoryCount", categoryService.getTotalCategories());
        request.setAttribute("blogCount", blogService.getTotalBlogs());
        request.setAttribute("linkCount", linkService.getTotalLinks());
        request.setAttribute("tagCount", tagService.getTotalTags());
        request.setAttribute("commentCount", commentService.getTotalComments());
        request.setAttribute("path", "index");
        return "admin/index";
    }

    //识别后台登陆的用户，并返回功能栏
    @GetMapping("profile")
    public String profile(HttpServletRequest request){
        Integer loginUserId = (int)request.getSession().getAttribute("loginUserId");
        //用Id查询该用户信息
        AdminUser adminUser = adminUserService.getUserDetailById(loginUserId);
        if (adminUser == null){
            return "admin/login";
        }
        request.setAttribute("path","profile");
        request.setAttribute("loginUserName",adminUser.getLoginUserName());
        request.setAttribute("nickName",adminUser.getNickName());
        return "admin/profile";
    }

    //修改管理员密码
    @PostMapping("/profile/password")
    @ResponseBody
    public String passwordUpdate(HttpServletRequest request, @RequestParam("originalPassword") String originalPassword,
                                 @RequestParam("newPassword") String newPassword){
        if (StringUtils.isEmpty(originalPassword) || StringUtils.isEmpty(newPassword)){
            return "密码不能为空";
        }
        Integer loginUserId = (int)request.getSession().getAttribute("loginUserId");
        if (adminUserService.updatePassowrd(loginUserId, originalPassword, newPassword)){
            request.getSession().removeAttribute("loginUserId");
            request.getSession().removeAttribute("loginUser");
            request.getSession().removeAttribute("errorMsg");
            return "success";
        }else {
            return "修改失败";
        }
    }

    //修改信息
    @PostMapping("/profile/name")
    @ResponseBody
    public String nameUpdate(HttpServletRequest request, @RequestParam("loginUserName") String loginUserName,
                             @RequestParam("nickName") String nickName){
        Integer loginUserId = (int)request.getSession().getAttribute("loginUserId");
        if (adminUserService.updateName(loginUserId, loginUserName, nickName)){
            return "success";
        }else {
            return "修改失败";
        }
    }

    //退出登陆
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("loginUserId");
        request.getSession().removeAttribute("loginUser");
        request.getSession().removeAttribute("errorMsg");
        return "admin/login";
    }

}
