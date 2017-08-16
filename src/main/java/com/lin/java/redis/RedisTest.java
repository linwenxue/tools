package com.lin.java.redis;


import redis.clients.jedis.Jedis;

/**
 * Created by linwenxue on 2014/10/15.
 */
public class RedisTest {
    private static Jedis jedis = new Jedis("192.168.0.2", 6379);

    public static void main(String[] args){
        jedis.connect();
        jedis.set("key1","123");
        String value = jedis.get("key1");
        jedis.del("key1");
        jedis.close();
        System.out.println(value);
    }
}
