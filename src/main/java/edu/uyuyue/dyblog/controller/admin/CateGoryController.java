package edu.uyuyue.dyblog.controller.admin;

import edu.uyuyue.dyblog.entity.BlogCategory;
import edu.uyuyue.dyblog.service.CategoryService;
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
 * Created by DuanYuan on 2019-09-29 15:48:27
 * Copyright © 2019 DuanYuan. All rights reserved.
 */

@Controller
@RequestMapping("/admin")
public class CateGoryController {

    @Resource
    private CategoryService cateGoryService;

    @GetMapping("/categories")
    public String categoryPage(HttpServletRequest request){
        request.setAttribute("path","categories");
        return "admin/category";

    }


    //分类列表
    @RequestMapping(value = "/categories/list", method = RequestMethod.GET)
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params){
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit")) || StringUtils.isEmpty(params.get("order"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        String temp = null;
        switch (params.get("sidx").toString()){
            case "categoryName":
                temp = "category_name";
                break;
            case "createTime":
                temp = "create_time";
                break;
        }
        params.put("sidx", temp);
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(cateGoryService.getBlogCategoryPage(pageUtil));
    }

    //分类添加
    @RequestMapping(value = "/categories/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(@RequestParam("categoryName") String categoryName,
                       @RequestParam("categoryIcon") String categoryIcon){
        if (StringUtils.isEmpty(categoryName)){
            return ResultGenerator.genFailResult("请输入分类名称！");
        }
        if (StringUtils.isEmpty(categoryIcon)){
            return ResultGenerator.genFailResult("请选择分类图标！");
        }
        if (cateGoryService.saveCategory(categoryName, categoryIcon)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("分类名称重复");
        }
    }

    //分类删除
    @RequestMapping(value = "/categories/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@RequestBody Integer[] ids){
        if (ids.length <1){
            return ResultGenerator.genFailResult("参数异常！");
        }
        if (cateGoryService.deleteBatch(ids)){
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("删除失败！");
        }
    }

    //分类修改
    @RequestMapping(value = "/categories/update", method = RequestMethod.POST)
    @ResponseBody
    public Result update(@RequestParam("categoryId") Integer categoryId,
                         @RequestParam("categoryName") String categoryName,
                         @RequestParam("categoryIcon") String categoryIcon){
        if (categoryId == null || categoryId <1){
            return ResultGenerator.genFailResult("非法参数！");
        }
        if (StringUtils.isEmpty(categoryName)) {
            return ResultGenerator.genFailResult("请输入分类名称！");
        }
        if (StringUtils.isEmpty(categoryIcon)) {
            return ResultGenerator.genFailResult("请选择分类图标！");
        }
        if (cateGoryService.updateCategory(categoryId, categoryName, categoryIcon)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("分类名称重复");
        }
    }

    //根据id获取详情
    @GetMapping("/categories/info/{id}")
    @ResponseBody
    public Result info(@PathVariable("id") Integer id){
        if (id == null || id<1){
            return ResultGenerator.genFailResult("非法参数！");
        }
        BlogCategory category = cateGoryService.selectById(id);
        return ResultGenerator.genSuccessResult();
    }
}
