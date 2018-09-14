package com.life.full.danger.user.service.impl;

import com.life.full.danger.user.dao.UserDao;
import com.life.full.danger.user.dao.UserMapper;
import com.life.full.danger.user.model.Car;
import com.life.full.danger.user.model.User;
import com.life.full.danger.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 分页：PageHelper.startPage(pageNum, pageSize);
 * @author LiHongHui
 * @date 2018/8/28 14:25
 * @description
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserDao userDao;

    @Override
    public int insert(User record) {
        return userDao.insert(record);
    }

    @Override
    public List<User> findAllUser(int pageNum, int pageSize) {
        return userDao.findAll();
    }

    @Override
    public String getUserAddress(int userId) {
        User user = userDao.findUserWithAddress(userId);
        return user.getAddress().toString();
    }

    @Override
    public List<Car> getCars(int userId) {
        User user = userDao.getUserWithCar(userId);
        return user.getCars();
    }
}
