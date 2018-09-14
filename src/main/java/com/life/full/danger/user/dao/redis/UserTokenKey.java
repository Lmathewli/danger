package com.life.full.danger.user.dao.redis;

import com.life.full.danger.base.redis.PrefixAbstract;

/**
 * @author LiHongHui
 * @date 2018/8/20 20:11
 * @description
 */
public class UserTokenKey extends PrefixAbstract {

    public UserTokenKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
    public static UserTokenKey getByUserName = new UserTokenKey(0, "chUserName");
}
