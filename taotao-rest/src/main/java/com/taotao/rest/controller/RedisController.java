package com.taotao.rest.controller;

import com.taotao.commom.utils.TaotaoResult;
import com.taotao.rest.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * User: H
 * Date: 2019/3/7
 * Time: 14:38
 * Description: No Description
 */
@Controller
@RequestMapping("cache")
public class RedisController {
    @Autowired
    private RedisService redisService;

    @RequestMapping("/content/{contentCid}")
    @ResponseBody
    public TaotaoResult syncContent(@PathVariable Long contentCid) {
        return redisService.syncContent(contentCid);
    }
}
