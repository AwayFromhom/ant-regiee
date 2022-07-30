package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
    /**
     * 修改菜品同时修改口味
     * @param dto
     * @param flavorService
     */
    @Override
    public void updateWithFlavor(DishDto dto, DishFlavorService flavorService) {
        this.updateById(dto);

        //得到dishId,根据dishId对对应的flavor进行修改
        Integer id = dto.getId();
        //删除flavor中所有的改id对应的值
        //添加查询条件，根据分类id查询
        LambdaQueryWrapper<DishFlavor> dishqueryWrapper = new LambdaQueryWrapper();
        dishqueryWrapper.eq(DishFlavor::getDishId, id);
        //删除
        flavorService.remove(dishqueryWrapper);
        //添加新的flavor属性
        ArrayList<DishFlavor> flavors = dto.getFlavors();
        if (flavors.size() > 0) {
            for (DishFlavor flavor : flavors) {
                //如果集合里面没有相同的元素才往里存
                flavor.setDishId(id);
                //保存菜品口味到菜品口味表
                flavorService.save(flavor);
            }
        }
    }

    @Override
    public void removeWithFlavor(ArrayList<Integer> ids, DishFlavorService flavorService) {
        /*
        首先删除菜品
         */
        this.removeByIds(ids);

        //删除flavor
        for (Integer id : ids) {
            //添加查询条件，根据分类id查询
            LambdaQueryWrapper<DishFlavor> dishqueryWrapper = new LambdaQueryWrapper();
            dishqueryWrapper.eq(DishFlavor::getDishId, id);
            //删除
            flavorService.remove(dishqueryWrapper);
        }

    }

}
