package com.itheima.reggie.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.common.CustomException;
import com.itheima.reggie.entity.Category;
import com.itheima.reggie.entity.Dish;
import com.itheima.reggie.entity.Setmeal;
import com.itheima.reggie.mapper.CategoryMapper;
import com.itheima.reggie.service.CategoryService;
import com.itheima.reggie.service.DishService;
import com.itheima.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceimpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishService dishService;
    @Autowired
    private SetmealService setmealService;

    @Override
    public void remove(Integer id) {
        //添加查询条件，根据分类id查询
        LambdaQueryWrapper<Dish> dishqueryWrapper = new LambdaQueryWrapper();
        dishqueryWrapper.eq(Dish::getCategoryId,id);

        int dishcount = dishService.count(dishqueryWrapper);

        if (dishcount > 0){
            //已经关联菜品抛出一个异常
            throw new CustomException("当前分类关联了菜品，无法删除。");
        }

        //添加查询条件，根据分类id查询
        LambdaQueryWrapper<Setmeal> setmealqueryWrapper = new LambdaQueryWrapper();
        setmealqueryWrapper.eq(Setmeal::getCategoryId,id);

        int setmealcount = setmealService.count(setmealqueryWrapper);

        if (setmealcount > 0){
            //已经关联套餐抛出一个异常
            throw new CustomException("当前分类关联了套餐，无法删除。");
        }

        // 正常删除
        super.removeById(id);

    }
}
