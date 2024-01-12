package com.barun.ff4j.configuration;

import org.ff4j.FF4j;
import org.ff4j.cache.FF4JCacheManager;
import org.ff4j.cache.FF4jCacheManagerRedis;
import org.ff4j.redis.RedisConnection;
import org.ff4j.springjdbc.store.EventRepositorySpringJdbc;
import org.ff4j.springjdbc.store.FeatureStoreSpringJdbc;
import org.ff4j.springjdbc.store.PropertyStoreSpringJdbc;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FF4jConfig {


    @Bean
    public FF4jCacheManagerRedis cacheManagerRedis(@Value("${ff4j.cache.redis.host}") String host, @Value("${ff4j.cache.redis.port}") int port) {
        RedisConnection redisConnection = new RedisConnection(host, port);
        redisConnection.setRedisPoolMaxTotal(10);
        redisConnection.setRedisPoolTimeout(6000);
        return new FF4jCacheManagerRedis(redisConnection);
    }

    @Bean
    public FF4j getFF4j(final DataSource dataSource, final FF4JCacheManager cacheManager) {
        FF4j ff4j = new FF4j();


        ff4j.setFeatureStore(new FeatureStoreSpringJdbc(dataSource));
        ff4j.setPropertiesStore(new PropertyStoreSpringJdbc(dataSource));
        ff4j.setEventRepository(new EventRepositorySpringJdbc(dataSource));
        ff4j.cache(cacheManager);

        // Enabling audit and monitoring, default value is false
        ff4j.audit(true);

        // When evaluting not existing features, ff4j will create then but disabled
        ff4j.autoCreate(true);

        // To define RBAC access, the application must have a logged user
        //ff4j.setAuthManager(...);

        // To define a cacher layer to relax the DB, multiple implementations
        //ff4j.cache([a cache Manager]);

        return ff4j;
    }
}