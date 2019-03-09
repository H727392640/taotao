package com.taotao.search.controller;

import com.taotao.commom.utils.TaotaoResult;
import com.taotao.search.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * User: H
 * Date: 2019/3/8
 * Time: 14:45
 * Description: No Description
 */
@Controller
@RequestMapping("/manager")
public class ItemController {

    @Autowired
    ItemService itemService;

    @ResponseBody
    @RequestMapping("/importall")
    public TaotaoResult importAllItems() {
        return itemService.importAllItems();
    }
}
