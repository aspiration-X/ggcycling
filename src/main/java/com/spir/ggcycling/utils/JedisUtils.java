package com.spir.ggcycling.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisUtils {

    static JedisPool jedisPool = new JedisPool();

    public  static  Jedis getJedisFromPool(){

        Jedis resource = jedisPool.getResource();

        return  resource;

    }



}
