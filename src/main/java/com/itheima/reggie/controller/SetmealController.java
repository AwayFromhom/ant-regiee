package com.itheima.reggie.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.R;
import com.itheima.reggie.dto.SetmealDto;
import com.itheima.reggie.entity.Category;
import com.itheima.reggie.entity.Setmeal;
import com.itheima.reggie.service.CategoryService;
import com.itheima.reggie.service.SetmealDishService;
import com.itheima.reggie.service.SetmealService;
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
@RequestMapping(value = "/api/setmeal",produces = "application/json;charset=utf-8")
public class SetmealController {
    @Autowired
    private SetmealService setmealService;
    @Autowired
    private SetmealDishService setmealDishService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 添加新套餐
     * @param setmealDto
     * @return
     */
    @PostMapping
    public R<String> adddish(@RequestBody SetmealDto setmealDto){
        setmealService.saveWithDish(setmealDishService,setmealDto);
        System.out.println(setmealDto);
        return R.success("good");
    }

    /**
     *
     * @param current
     * @param pageSize
     * @param name
     * @param categoryName
     * @return
     */
    @GetMapping("/page")
    public String getemployees(int current, int pageSize, String name,String categoryName){

        //构造分页构造器
        Page<Setmeal> pageinfo = new Page(current,pageSize);


        //构造条件构造器
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper();

        //1.先查出来setmeal的分页数据

        //当categoryName有值时查出对应的categoryId进行查询
        if(categoryName!=null){
            //根据categoryName查询数据库
            //构造条件构造器
            LambdaQueryWrapper<Category> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.like(Category::getName,categoryName);
            Category category = categoryService.getOne(queryWrapper1);

            System.out.println(category.getId());

            //添加过滤条件
            queryWrapper.like(name!=null,Setmeal::getName,name)
                    .eq(Setmeal::getCategoryId,category.getId());
        }
        //当categoryName没有值时直接添加过滤条件
        else  queryWrapper.like(name!=null,Setmeal::getName,name);
        //排序条件
        queryWrapper.orderByDesc(Setmeal::getCreateTime);
        //执行查询
        setmealService.page(pageinfo,queryWrapper);

        //2.添加对相应的dish到dto中
        //构造dto分页构造器
        Page<SetmealDto> dtopageinfo = new Page();

        //对象拷贝
        BeanUtils.copyProperties(pageinfo,dtopageinfo, "records");


        List<Setmeal> records = pageinfo.getRecords();
        List<SetmealDto> list = records.stream().map(
                (item) -> {
                    SetmealDto dishDto = new SetmealDto();
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
    public R<String> update (HttpServletRequest request, @RequestBody SetmealDto dto){
//        修改两处
        setmealService.updateWithDish(dto,setmealDishService);


        return R.success("ok");
    }

    @DeleteMapping
    public R<String> delete (HttpServletRequest request,@RequestBody ArrayList<Integer> keys){
        /*
        删除操作
        1.首先删除setmeal
        2.删除口味表中的口味
         */
        setmealService.removeWithDish(keys,setmealDishService);
        return  R.success("删除成功！");
    }


}
