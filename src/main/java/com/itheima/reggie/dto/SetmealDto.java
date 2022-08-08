package com.itheima.reggie.dto;

import com.itheima.reggie.entity.Setmeal;
import com.itheima.reggie.entity.SetmealDish;
import lombok.Data;

import java.util.ArrayList;


@Data
public class SetmealDto extends Setmeal {

    private ArrayList<SetmealDish> setmealDishes;

    private String categoryName;
}
