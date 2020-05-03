package edu.uyuyue.dyblog.service;

import edu.uyuyue.dyblog.entity.BlogCategory;
import edu.uyuyue.dyblog.util.PageQueryUtil;
import edu.uyuyue.dyblog.util.PageResult;

import java.util.List;

public interface CategoryService {

    //查询分类的分页数据
    PageResult getBlogCategoryPage(PageQueryUtil pageUtil);

    boolean saveCategory(String categoryName, String categoryIcon);

    boolean deleteBatch(Integer[] ids);

    boolean updateCategory(Integer categoryId, String categoryName, String categoryIcon);

    BlogCategory selectById(Integer id);

    List<BlogCategory> getAllCategories();

    int getTotalCategories();
}
