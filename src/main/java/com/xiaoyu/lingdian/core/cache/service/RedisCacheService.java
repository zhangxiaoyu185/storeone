package com.xiaoyu.lingdian.core.cache.service;

import java.util.List;

import com.xiaoyu.lingdian.core.cache.model.RedisKeyAndValue;

public interface RedisCacheService {

	/**
     * 添加
     */
    public boolean set(final String key, final String value);
    
    /**
     * 添加
     */
    public boolean set(final String key, final String value, final Long expireTime);
    
    /**
     * 添加对象
     */
    public boolean set(final RedisKeyAndValue redisKeyAndValue); 

    /**
     * 添加集合
     */
    public boolean set(final List<RedisKeyAndValue> list);  
    
    /**
     * 删除对象 ,依赖key
     */
    public void delete(String key);
  
    /**
     * 删除集合 ,依赖key集合
     */
    public void delete(List<String> keys);
    
    /**
     * 修改对象 
     */
    public boolean update(final RedisKeyAndValue redisKeyAndValue);
    
    /**
     * 根据key获取对象
     */
    public RedisKeyAndValue get(final String key);

    /**
     * 根据key获取值,不存在返回null
     */
    public String getString(final String key);
    
}
