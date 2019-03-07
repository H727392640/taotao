package com.taotao.controller;

import com.taotao.commom.utils.TaotaoResult;
import com.taotao.common.pojo.EUDateGridResult;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * User: H
 * Date: 2019/3/4
 * Time: 20:49
 * Description: No Description
 */
@Controller
@RequestMapping("/item/param")
public class ItemParamController {

    @Autowired
    ItemParamService itemParamService;

    @RequestMapping("/query/itemcatid/{itemcatid}")
    @ResponseBody
    public TaotaoResult getItemParamByCid(@PathVariable long itemcatid) {
        return itemParamService.getItemParamByCid(itemcatid);
    }

    @RequestMapping(value = "/save/{cid}", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult saveItemParam(@PathVariable long cid, String paramData) {

        TbItemParam tbItemParam = new TbItemParam();
        tbItemParam.setItemCatId(cid);
        tbItemParam.setParamData(paramData);

        return itemParamService.insertItemParam(tbItemParam);

    }

    @RequestMapping("/list")
    @ResponseBody
    public EUDateGridResult getItemParamList(Integer page, Integer rows){
        return itemParamService.getItemParamList(page, rows);
    }
}
