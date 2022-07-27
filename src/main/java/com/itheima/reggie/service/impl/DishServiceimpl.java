package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.dto.DishDto;
import com.itheima.reggie.entity.Dish;
import com.itheima.reggie.entity.DishFlavor;
import com.itheima.reggie.mapper.DishMapper;
import com.itheima.reggie.service.DishFlavorService;
import com.itheima.reggie.service.DishService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
public class DishServiceimpl extends ServiceImpl<DishMapper, Dish> implements DishService {
    /**
     * 新增菜品同时保存口味数据
     * @param dto
     */
    @Override

    public void saveWithFlavor(DishDto dto, DishFlavorService flavorService) {
        //保存基本信息到菜品表
        this.save(dto);
        Integer id = dto.getId();

        ArrayList<DishFlavor> flavors = dto.getFlavors();
        for (DishFlavor flavor : flavors) {
            //如果集合里面没有相同的元素才往里存
                flavor.setDishId(id);
                //保存菜品口味到菜品口味表
                flavorService.save(flavor);
        }
    }

}
