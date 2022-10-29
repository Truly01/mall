package com.truly.mall.service;

/**
 * @author truly
 * @date 2022/10/22 23:57
 * redis操作接口
 */
public interface RedisService {
    /**
     * 存储数据
     * @param key
     * @param value
     */
    void set(String key,String value);

    /**
     * 获取数据
     * @param key
     */
    String get(String key);

    /**
     * 设置超时时间
     * @param key
     * @param expire
     */
    void expire(String key,long expire);

    /**
     * 删除数据
     * @param key
     */
    void delete(String key);

    /**
     * 自增操作
     * @param key
     * @param delta 自增步长
     * @return
     */
    Long increment(String key,long delta);
}
