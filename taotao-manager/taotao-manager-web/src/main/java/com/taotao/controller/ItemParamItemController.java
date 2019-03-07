package com.taotao.controller;

import com.taotao.service.ItemParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: H
 * Date: 2019/3/4
 * Time: 22:08
 * Description: No Description
 */
@Controller
public class ItemParamItemController {

    @Autowired
    private ItemParamItemService itemParamItemService;

    @RequestMapping("/show/item/{itemid}")
    public String showItemParam(@PathVariable long itemid, Model model){
        String string =  itemParamItemService.getItemParamByItemId(itemid);
        model.addAttribute("itemParam", string);
        return "item";
    }
}
