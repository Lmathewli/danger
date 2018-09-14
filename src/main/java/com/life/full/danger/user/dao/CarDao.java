package com.life.full.danger.user.dao;

import com.life.full.danger.user.model.Car;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author LiHongHui
 * @date 2018/8/28 20:42
 * @description
 */
public interface CarDao {
    /**
     * 根据用户id查询所有的车
     */
    @Select("SELECT * FROM `car` WHERE user_id = #{userId}")
    List<Car> findCarByUserId(Integer userId);
}
