package com.life.full.danger.base.redis;

/**
 * @author LiHongHui
 * @date 2018/8/20 20:06
 * @description
 */
public interface Prefix {
    /**
     * 过期时间
     * @return
     */
    int expireSeconds();

    /**
     * key的前缀
     * @return
     */
    String getPrefix();
}
