package com.hacker.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Description
 * @Author mr.mao_hacker
 * @Date 2021/1/26
 * @Version 1.0
 */
@Configuration
public class RedisConfig {

    @Value("${spring.redis.database}")
    private int dbIndex;

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.password}")
    private String password;

    @Bean
    @Primary
        // 若存在多个实现类优先使用该类
    JedisConnectionFactory defaultConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(host, 6379);
        config.setDatabase(dbIndex);
        config.setPassword(password);
        return new JedisConnectionFactory(config);
    }

    @Bean(name = "defaultRedisTemplate")
    public RedisTemplate<String, Object> initRedisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        redisTemplate.setConnectionFactory(this.defaultConnectionFactory());
        redisTemplate.setKeySerializer(stringSerializer);
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        return redisTemplate;
    }

    @Bean(name = "myStringRedisTemplate")
    public RedisTemplate<String, String> initStringRedisTemplate() {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        redisTemplate.setConnectionFactory(this.defaultConnectionFactory());
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        return redisTemplate;
    }

}
