package com.herlandio7.stock.infra.persistence.cache;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class ProcessedProductsCache {

    private final RedisTemplate<String, Long> redisTemplate;
    private static final String CACHE_KEY = "processedProducts";

    public ProcessedProductsCache(RedisTemplate<String, Long> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean isProcessed(Long productId) {
        return redisTemplate.opsForSet().isMember(CACHE_KEY, productId);
    }

    public void markAsProcessed(Long productId) {
        redisTemplate.opsForSet().add(CACHE_KEY, productId);
        redisTemplate.expire(CACHE_KEY, 1, TimeUnit.DAYS);
    }
}
