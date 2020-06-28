package com.juho.spm.repository;

import com.juho.spm.dto.ReceiveMoneyInfo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.util.concurrent.TimeUnit;

@Repository
public class SplashRepository {

    private RedisTemplate<String, ReceiveMoneyInfo> splashMoneyRedisTemplate;
    private SetOperations<String, ReceiveMoneyInfo> setOperations;

    public SplashRepository(RedisTemplate<String, ReceiveMoneyInfo> splashMoneyRedisTemplate) {
        this.splashMoneyRedisTemplate = splashMoneyRedisTemplate;
        this.setOperations = splashMoneyRedisTemplate.opsForSet();
    }

    public void add(String key, ReceiveMoneyInfo receiveMoneyInfo) {
        setOperations.add(key, receiveMoneyInfo);
        splashMoneyRedisTemplate.expire(key, 7, TimeUnit.DAYS);
    }

    public ReceiveMoneyInfo get(String key) {
        return setOperations.pop(key);
    }
}
