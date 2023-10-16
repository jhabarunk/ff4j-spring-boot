package com.barun.ff4j.configuration;

import org.ff4j.cache.FF4JCacheManager;
import org.ff4j.cache.FF4jCacheManagerRedis;
import org.ff4j.redis.RedisConnection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisCacheManager {
    @Value("${ff4j.cache.redis.host}")
    private String redisHostName;

    @Value("${ff4j.cache.redis.port}")
    private int redisPort;

    @Bean
    public FF4JCacheManager getFF4jCacheManager() {
        RedisConnection redisConnection = new RedisConnection(redisHostName, redisPort);
        return new FF4jCacheManagerRedis(redisConnection);
    }
}
