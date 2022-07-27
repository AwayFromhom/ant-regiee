package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.dto.DishDto;
import com.itheima.reggie.entity.Dish;

public interface DishService  extends IService<Dish> {
    /**
     * 新增菜品同时添加口味
     * @param dto
     */
    public void saveWithFlavor(DishDto dto, DishFlavorService flavorService);
}
