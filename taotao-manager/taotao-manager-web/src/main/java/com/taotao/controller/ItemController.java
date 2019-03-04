package com.taotao.controller;

import com.taotao.commom.utils.TaotaoResult;
import com.taotao.common.pojo.EUDateGridResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable Long itemId){
        return itemService.getItemById(itemId);
    }

    @RequestMapping("/list")
    @ResponseBody
    public EUDateGridResult getItemList(Integer page, Integer rows){
        return itemService.getItemList(page, rows);
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult createItem (TbItem tbItem, String desc)throws Exception{
        return itemService.createItem(tbItem, desc);
    }
}
