package com.taotao.rest.service.impl;

import com.taotao.commom.utils.TaotaoResult;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: H
 * Date: 2019/3/7
 * Time: 14:35
 * Description: No Description
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private JedisClient jedisClient;

    @Value("${INDEX_CONTENT_REDIS_KEY}")
    private String INDEX_CONTENT_REDIS_KEY;

    @Override
    public TaotaoResult syncContent(Long contentId) {
        jedisClient.hdel(INDEX_CONTENT_REDIS_KEY, contentId+"");
        return TaotaoResult.ok();
    }
}
