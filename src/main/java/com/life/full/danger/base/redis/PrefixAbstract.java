package com.life.full.danger.base.redis;

/**
 * @author LiHongHui
 * @date 2018/8/20 20:08
 * @description
 */
public abstract class PrefixAbstract implements Prefix {

    private int expireSeconds;
    private String prefix;

    public PrefixAbstract(String prefix) {
        this.prefix = prefix;
    }

    public PrefixAbstract(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    /**
     * 过期时间
     * 默认0：代表永不过期
     * @return
     */
    @Override
    public int expireSeconds() {
        return expireSeconds;
    }

    /**
     * key的前缀
     *
     * @return
     */
    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className + ":" + prefix;
    }
}
