package com.life.full.danger.user.controller;

import com.life.full.danger.user.dao.redis.UserRedisDao;
import com.life.full.danger.user.dao.redis.UserTokenKey;
import com.life.full.danger.user.model.User;
import com.life.full.danger.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiHongHui
 * @date 2018/8/28 14:30
 * @description
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRedisDao userRedisDao;

    @RequestMapping("/add")
    public Integer addUser(User user) {
        int id = userService.insert(user);
        return id;
    }

    @RequestMapping("/all/{pageNum}/{pageSize}")
    public Object findAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
        return userService.findAllUser(pageNum, pageSize);
    }

    @RequestMapping("/address")
    public Object address(int userId) {
        return userService.getUserAddress(userId);
    }

    @RequestMapping("/cars")
    public Object cars(int userId) {
        return userService.getCars(userId);
    }

    @RequestMapping("/get")
    public String getToken() {
        String chUserName = "测试酒店";
        return userRedisDao.getValue(UserTokenKey.getByUserName, chUserName);
    }
}
