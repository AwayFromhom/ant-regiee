package com.itheima.reggie.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.R;
import com.itheima.reggie.dto.DishDto;
import com.itheima.reggie.entity.Category;
import com.itheima.reggie.entity.Dish;
import com.itheima.reggie.service.CategoryService;
import com.itheima.reggie.service.DishFlavorService;
import com.itheima.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "/api/dish",produces = "application/json;charset=utf-8")
public class DishController {
    @Autowired
    private DishService dishService;
    @Autowired
    private DishFlavorService dishFlavorService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 添加菜品信息
     * @param dto
     * @return
     */
    @PostMapping
    public R<String> adddish(@RequestBody DishDto dto) {

    log.info("Adding dish to service");
    dishService.saveWithFlavor(dto,dishFlavorService);

        return R.success("dto");
    }

    @GetMapping("/page")
    public String getemployees(int current, int pageSize, String name,String categoryName){

        //构造分页构造器
        Page<Dish> pageinfo = new Page(current,pageSize);
        Page<DishDto> dtopageinfo = new Page();

        //构造条件构造器
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper();

        if(categoryName!=null){
            //1.当categoryName有值时查出对应的categoryId
            //2、根据categoryName查询数据库
            LambdaQueryWrapper<Category> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(Category::getName,categoryName);
            Category category = categoryService.getOne(queryWrapper1);

            System.out.println(category.getId());

            //添加过滤条件
            queryWrapper.like(name!=null,Dish::getName,name)
                    .like(Dish::getCategoryId,category.getId());

        }

        else  queryWrapper.like(name!=null,Dish::getName,name);//添加过滤条件

        //排序条件
        queryWrapper.orderByDesc(Dish::getCreateTime);
        //执行查询
        dishService.page(pageinfo,queryWrapper);

        //对象拷贝
        BeanUtils.copyProperties(pageinfo,dtopageinfo, "records");

        List<Dish> records = pageinfo.getRecords();
        List<DishDto> list = records.stream().map(
                (item) -> {
                    DishDto dishDto = new DishDto();
                    BeanUtils.copyProperties(item, dishDto);
                    Integer categoryId = item.getCategoryId();

                    //根据ID查找分类对象

                    Category category = categoryService.getById(categoryId);
                    String categoryName1 = category.getName();
                    dishDto.setCategoryName(categoryName1);

                    return dishDto;
                }
        ).collect(Collectors.toList());

        dtopageinfo.setRecords(list);

        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("total", pageinfo.getTotal());
        result.put("data",dtopageinfo.getRecords() );
        return result.toJSONString();
    }



    @PutMapping
    public R<String> update (HttpServletRequest request,@RequestBody DishDto dto){
        System.out.println(dto);
        return R.success("ok");
    }

    @DeleteMapping
    public R<String> delete (HttpServletRequest request,@RequestBody ArrayList<Integer> keys){

        for (Integer key : keys) {
            if(key > 10){


            }
        }
        return  R.success("删除成功！");
    }



}
