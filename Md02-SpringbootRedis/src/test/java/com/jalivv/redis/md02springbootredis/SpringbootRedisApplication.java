package com.jalivv.redis.md02springbootredis;

import com.alibaba.fastjson.JSONObject;
import com.jalivv.redis.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class SpringbootRedisApplication {

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void contextLoads() {

        /**
         * redisTemplate    操作不同的数据类型
         * opsForValue() 操作字符串的
         * opsForHash()
         * opsForSet()
         * opsForZset()
         * opsForGeo()
         * 除了基本的操作，我们常用的方法都可以直接通过 redisTemplate 操作，比如事务和基本的增删改查
         */

        //获取redis 的连接对象
        //RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        //connection.flushDb();
        //connection.flushAll();

        //redisTemplate.opsForValue().set("bootKey", "jalivv is a boy");
        //System.out.println(redisTemplate.opsForValue().get("bootKey"));

        redisTemplate.opsForValue().set("bootKey", "中国");
        System.out.println(redisTemplate.opsForValue().get("bootKey"));


    }

    @Test
    public void testUesr() {

        User user = new User("jalivv", 3);

        //redisTemplate.opsForValue().set("user", JSONObject.toJSONString(user));
        redisTemplate.opsForValue().set("user", user);
        System.out.println(redisTemplate.opsForValue().get("user"));

    }



}
