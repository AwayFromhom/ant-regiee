package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.dto.SetmealDto;
import com.itheima.reggie.entity.Setmeal;

import java.util.ArrayList;

public interface SetmealService  extends IService<Setmeal> {
    /**
     * 添加新套餐
     * @param service
     * @param dto
     */
    public void saveWithDish(SetmealDishService service, SetmealDto dto);

    public void updateWithDish(SetmealDto dto, SetmealDishService setmealDishService);

   public void removeWithDish(ArrayList<Integer> keys, SetmealDishService setmealDishService);
}
