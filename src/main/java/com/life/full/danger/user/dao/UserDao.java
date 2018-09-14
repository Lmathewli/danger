package com.life.full.danger.user.dao;

import com.life.full.danger.user.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author LiHongHui
 * @date 2018/8/28 17:52
 * @description
 */
public interface UserDao {
    /**
     * 查询所有
     * @return
     */
    @Select("select * from user")
    List<User> findAll();

    @Insert("insert into user(user_name, phone) values(#{userName}, #{phone})")
    @Options(useGeneratedKeys = true)
    int insert(User user);

    @Select("SELECT * FROM `user` where user_id = #{userId}")
    @Results({
            @Result(property = "address", column = "address_id", one = @One(select = "com.life.full.danger.user.dao.AddressMapper.findAddressById"))
    })
    User findUserWithAddress(int userId);

    @Select("SELECT * FROM `user` WHERE user_id = #{userId}")
    @Results({
            @Result(property = "cars", column = "user_id",
                    many = @Many(select = "com.life.full.danger.user.dao.CarDao.findCarByUserId"))
    })
    User getUserWithCar(int userId);
}
