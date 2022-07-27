package com.itheima.reggie.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.Category;
import com.itheima.reggie.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 分类管理
 */
@Slf4j
@RestController
@RequestMapping(value = "api/category",produces = "application/json;charset=utf-8")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 添加菜品分類
     * @param category
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody Category category){
        log.info("Saving category");
        categoryService.save(category);
        return R.success("Category saved!");
    }

    /**
     * 分页查询
     * @param current
     * @param pageSize
     * @param name
     * @param type
     * @return
     */
    @GetMapping("/page")
    public String getcategorys(int current, int pageSize, String name,Integer type){
        //构造分页构造器
        Page pageinfo = new Page(current,pageSize);
        //构造条件构造器
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper();
        //添加过滤条件
        queryWrapper.like(name!=null,Category::getName,name)
                .like(type!=null,Category::getType,type);
        //排序条件
        queryWrapper.orderByDesc(Category::getSort);

        //执行查询
        categoryService.page(pageinfo,queryWrapper);

        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("total", pageinfo.getTotal());
        result.put("data",pageinfo.getRecords() );
        return result.toJSONString();
    }

    @PutMapping
    public R<String> update (HttpServletRequest request, @RequestBody Category category){
        categoryService.updateById(category);
        return R.success("ok");
    }

    @DeleteMapping
    public R<String> delete (HttpServletRequest request,@RequestBody ArrayList<Integer> keys){
        System.out.println(keys);

        for (Integer key : keys) {

         if(key > 10){
               categoryService.remove(key);
            }
        }
            return  R.success("删除成功！");
    }


    /**
     * 根据type查询套餐
     * @param type
     * @return
     */
    @GetMapping("/querycategory/{type}")
    public R<List<Category>> getQueryCategory(@PathVariable(name="type") String type) {

        //条件构造器
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper();
        //添加条件 按照顺序排序
        queryWrapper.eq(Category::getType,type).orderByAsc(Category::getSort)
                .orderByDesc(Category::getCreateTime);

        //遍历分类表
        List<Category> list = categoryService.list(queryWrapper);

//        Category category = new Category();
//        category.setName("haha");
//        category.setId(5);
//        ArrayList<Category> categories = new ArrayList<Category>();
//        categories.add(category);

        return R.success(list);

    }


}
