package com.life.full.danger.user.dao.redis;

import com.life.full.danger.base.redis.Prefix;
import com.life.full.danger.base.redis.RedisBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

/**
 * @author LiHongHui
 * @date 2018/9/4 10:09
 * @description
 */
@Repository
public class UserRedisDao extends RedisBaseDao {
    @Autowired
    private RedisTemplate<String, String> template;

    public void setKey(Prefix prefix, String key, String value) {
        // 获取真实模板
        ValueOperations<String, String> operations = template.opsForValue();
        // 获取realKey
        String realKey = getRealKey(prefix, key);
        if (prefix.expireSeconds() <= 0) {
            operations.set(realKey, value);
        } else {
            operations.set(realKey, value, prefix.expireSeconds());
        }
    }
    public String getValue(Prefix prefix, String key) {
        String realKey = getRealKey(prefix, key);
        ValueOperations<String, String> operations = template .opsForValue();
        return operations.get(realKey);
    }
}
