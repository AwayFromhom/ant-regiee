package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.dto.DishDto;
import com.itheima.reggie.entity.Dish;

import java.util.ArrayList;

public interface DishService  extends IService<Dish> {
    /**
     * 新增菜品同时添加口味
     * @param dto
     */
    public void saveWithFlavor(DishDto dto, DishFlavorService flavorService);

    /**
     * 修改菜品同时修改口味
     * @param dto
     * @param flavorService
     */
    public void updateWithFlavor(DishDto dto,DishFlavorService flavorService);


    /**
     * 删除菜品同时删除口味
     * @param ids
     * @param flavorService
     */
    public void removeWithFlavor(ArrayList<Integer> ids, DishFlavorService flavorService);
}
