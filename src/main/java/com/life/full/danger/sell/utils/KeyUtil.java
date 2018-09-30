package com.life.full.danger.sell.utils;

import java.util.Random;

/**
 * @author LiHongHui
 * @date 2018/9/25 17:13
 * @description
 */
public class KeyUtil {
    /**
     * 生成唯一的主键
     * 格式：时间+ 随机数
     * @return
     */
    public static synchronized final String genUniqueKey() {
        Random random = new Random();
        // 生成6位随机数
        Integer randomValue = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(randomValue);
    }
}
