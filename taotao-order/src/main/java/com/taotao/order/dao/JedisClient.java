package com.taotao.order.dao;

/**
 * Created with IntelliJ IDEA.
 * User: H
 * Date: 2019/3/7
 * Time: 13:44
 * Description: No Description
 */

public interface JedisClient {

    String get(String key);
    String set(String key, String value);
    String hget(String hkey, String key);
    long hset(String hkey, String key, String value);
    long incr(String key);
    long expire(String key, int second);
    long ttl(String key);
    long del(String key);
    long hdel(String hkey, String key);

}
