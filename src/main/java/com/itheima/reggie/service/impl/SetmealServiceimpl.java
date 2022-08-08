package com.itheima.reggie.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.dto.SetmealDto;
import com.itheima.reggie.entity.Setmeal;
import com.itheima.reggie.entity.SetmealDish;
import com.itheima.reggie.mapper.SetmealMapper;
import com.itheima.reggie.service.SetmealDishService;
import com.itheima.reggie.service.SetmealService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SetmealServiceimpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
    /**
     * 添加新套餐
     * @param service
     * @param dto
     */
    public void saveWithDish(SetmealDishService service, SetmealDto dto){
        //保存基本信息到套餐表
        this.save(dto);
        Integer id = dto.getId();
        System.out.println(id);

        ArrayList<SetmealDish> dishes = dto.getSetmealDishes();
        for (SetmealDish dish : dishes) {
            //如果集合里面没有相同的元素才往里存
            dish.setSetmealId(id);
            //保存
            service.save(dish);
        }
    }

    /**
     * 修改套餐
     * @param dto
     * @param setmealDishService
     */
    public void updateWithDish(SetmealDto dto, SetmealDishService setmealDishService){
        this.updateById(dto);
        //得到SetmealId,根据SetmealId对对应的dish进行修改
        ArrayList<SetmealDish> setmealDishs = dto.getSetmealDishes();
        //如果SetmealDish有值则删除
        if (setmealDishs!=null) {
        //删除Setmealdish中所有的改SetmealId对应的值
            Integer id = dto.getId();
        //添加查询条件，根据分类id查询
        LambdaQueryWrapper<SetmealDish> dishqueryWrapper = new LambdaQueryWrapper();
        dishqueryWrapper.eq(SetmealDish::getSetmealId, id);
        //删除
        setmealDishService.remove(dishqueryWrapper);
        //添加新的SetmealDish属性

            for (SetmealDish setmealDish : setmealDishs) {
                //如果集合里面没有相同的元素才往里存
                setmealDish.setSetmealId(id);
                //保存菜品口味到菜品口味表
                setmealDishService.save(setmealDish);
            }
        }
    }

    public void removeWithDish(ArrayList<Integer> keys, SetmealDishService setmealDishService){
         /*
        首先删除菜品
         */
        this.removeByIds(keys);

        //删除SetmealDish
        for (Integer id : keys) {
            //添加查询条件，根据分类id查询
            LambdaQueryWrapper<SetmealDish> dishqueryWrapper = new LambdaQueryWrapper();
            dishqueryWrapper.eq(SetmealDish::getDishId, id);
            //删除
            setmealDishService.remove(dishqueryWrapper);
        }
    }
}
