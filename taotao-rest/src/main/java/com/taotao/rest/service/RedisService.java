package com.taotao.rest.service;

import com.taotao.commom.utils.TaotaoResult;

/**
 * Created with IntelliJ IDEA.
 * User: H
 * Date: 2019/3/7
 * Time: 14:34
 * Description: No Description
 */
public interface RedisService {
    TaotaoResult syncContent(Long contentId);
}
