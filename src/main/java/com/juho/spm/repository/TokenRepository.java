package com.juho.spm.repository;

import com.juho.spm.dto.TokenInfo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Repository
public class TokenRepository {
    private RedisTemplate<String, TokenInfo> tokenRedisTemplate;
    private SetOperations<String, TokenInfo> setOperations;

    public TokenRepository(RedisTemplate<String, TokenInfo> tokenRedisTemplate) {
        this.tokenRedisTemplate = tokenRedisTemplate;
        this.setOperations = tokenRedisTemplate.opsForSet();
    }

    public void add(String key, TokenInfo tokenInfo) {
        setOperations.add(key, tokenInfo);
        tokenRedisTemplate.expire(key, 7, TimeUnit.DAYS);
    }

    public Optional<TokenInfo> get(String key) {
        return Optional.of(setOperations.pop(key));
    }
}
