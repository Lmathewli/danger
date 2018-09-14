package com.life.full.danger.user.service;

import com.life.full.danger.user.model.Car;
import com.life.full.danger.user.model.User;

import java.util.List;

/**
 * @author LiHongHui
 * @date 2018/8/28 14:24
 * @description
 */
public interface UserService {
    int insert(User record);

    List<User> findAllUser(int pageNum, int pageSize);

    String getUserAddress(int userId);

    List<Car> getCars(int userId);
}
