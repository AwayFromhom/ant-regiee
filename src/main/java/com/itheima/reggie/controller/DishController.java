package com.itheima.reggie.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.R;
import com.itheima.reggie.dto.DishDto;
import com.itheima.reggie.entity.Dish;
import com.itheima.reggie.service.DishFlavorService;
import com.itheima.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/dish",produces = "application/json;charset=utf-8")
public class DishController {
    @Autowired
    private DishService dishService;
    @Autowired
    private DishFlavorService dishFlavorService;

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
    public String getemployees(int current, int pageSize, String name){
        //构造分页构造器
        Page<Dish> pageinfo = new Page(current,pageSize);
        Page<DishDto> dtopageinfo = new Page();

        //构造条件构造器
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper();
        //添加过滤条件
        queryWrapper.like(name!=null,Dish::getName,name);
        //排序条件
        queryWrapper.orderByDesc(Dish::getCreateTime);
        //执行查询
        dishService.page(pageinfo,queryWrapper);

        //对象拷贝
        BeanUtils.copyProperties(pageinfo,dtopageinfo, "records");

        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("total", pageinfo.getTotal());
        result.put("data",pageinfo.getRecords() );
        return result.toJSONString();
    }


}
