package edu.uyuyue.dyblog.controller.admin;

import edu.uyuyue.dyblog.service.TagService;
import edu.uyuyue.dyblog.util.PageQueryUtil;
import edu.uyuyue.dyblog.util.Result;
import edu.uyuyue.dyblog.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by DuanYuan on 2019-09-29 19:20:22
 * Copyright © 2019 DuanYuan. All rights reserved.
 */

@Controller
@RequestMapping("/admin")
public class TagController {

    @Resource
    private TagService tagService;

    @GetMapping("/tags")
    public String tagPage(HttpServletRequest request) {
        request.setAttribute("path", "tags");
        return "admin/tag";
    }

    //获取分页信息并获取列表
    @GetMapping("/tags/list")
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params){
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))){
            return ResultGenerator.genFailResult("参数异常！");
        }
        String temp = null;
        switch (params.get("sidx").toString()){
            case "tagName":
                temp = "tag_name";
                break;
            case "createTime":
                temp = "create_time";
                break;
        }
        params.put("sidx", temp);
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(tagService.getBlogTagPage(pageUtil));
    }

    //添加标签
    @PostMapping("/tags/save")
    @ResponseBody
    public Result save(@RequestParam("tagName") String tagName){
        if (StringUtils.isEmpty(tagName)){
            return ResultGenerator.genFailResult("参数异常！");
        }
        if (tagService.saveTag(tagName)){
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("标签名重复");
        }
    }

    //删除标签
    @PostMapping("/tags/delete")
    @ResponseBody
    public Result delete(@RequestBody Integer[] ids){
        if (ids.length < 1){
            return ResultGenerator.genFailResult("参数异常!");
        }
        if (tagService.deleteBatch(ids)){
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("有关联数据请勿强行删除");
        }

    }

}
