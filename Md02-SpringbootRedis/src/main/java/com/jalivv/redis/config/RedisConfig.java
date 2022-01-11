package com.jalivv.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @Description redis配置类
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/9 15:37
 */
@Configuration
public class RedisConfig {


    @Bean("redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {

        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setStringSerializer(RedisSerializer.string());
        template.setValueSerializer(RedisSerializer.json());
        template.setDefaultSerializer(RedisSerializer.string());
        template.afterPropertiesSet();

        return template;
    }



}
