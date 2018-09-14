package com.life.full.danger.base.redis;

/**
 * @author LiHongHui
 * @date 2018/9/4 11:58
 * @description
 */
public class RedisBaseDao {
    public String getRealKey(Prefix redisPrefix, String key) {
        return redisPrefix.getPrefix() + key;
    }
}
