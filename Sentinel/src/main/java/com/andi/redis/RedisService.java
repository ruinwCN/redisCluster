package com.andi.redis;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

/**
 * Created by Percy on 2020-04-15.
 */

@Service
public class RedisService {

    public String RedisTest() {
        Set<String> sentinels = new HashSet<String>();
        sentinels.add("172.20.0.5:26379");
        sentinels.add("172.20.0.6:26379");
        sentinels.add("172.20.0.7:26379");

        String clusterName = "mymaster";
        String password = "123456";

        JedisSentinelPool redisSentinelJedisPool = new JedisSentinelPool(clusterName, sentinels, password);

        Jedis jedis = null;
        String value = "";
        try {
            jedis = redisSentinelJedisPool.getResource();
            jedis.set("key", "value");
            value = jedis.get("key");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redisSentinelJedisPool.returnBrokenResource(jedis);
        }

        redisSentinelJedisPool.close();
        return value;
    }
}
