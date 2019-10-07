package hhx.dao;

import hhx.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

public class RedisTest extends BaseTest {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void testConnect(){
        redisTemplate.opsForValue().set("name", "test");
        System.out.println(redisTemplate.opsForValue().get("name"));
    }
}
