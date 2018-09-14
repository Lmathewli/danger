package com.life.full.danger.user.dao;

import com.life.full.danger.user.model.Address;
import org.apache.ibatis.annotations.Select;

/**
 * @author LiHongHui
 * @date 2018/8/28 20:39
 * @description
 */
public interface AddressMapper {
    @Select("SELECT * FROM `address` WHERE id = #{id}")
    Address findAddressById(Long id);
}
