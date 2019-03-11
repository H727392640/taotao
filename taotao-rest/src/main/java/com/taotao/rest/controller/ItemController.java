package com.taotao.rest.controller;

import com.taotao.commom.utils.TaotaoResult;
import com.taotao.rest.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * User: H
 * Date: 2019/3/9
 * Time: 14:22
 * Description: No Description
 */
@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/info/{id}")
    @ResponseBody
    public TaotaoResult getItemBaseInfo(@PathVariable Long id ){
        return itemService.getItemBaseInfo(id);
    }

    @RequestMapping("/desc/{id}")
    @ResponseBody
    public TaotaoResult getItemDesc(@PathVariable Long id ){
        return itemService.getItemDesc(id);
    }

    @RequestMapping("/param/{id}")
    @ResponseBody
    public TaotaoResult getItemParam(@PathVariable Long id ){
        return itemService.getItemParam(id);
    }
}
