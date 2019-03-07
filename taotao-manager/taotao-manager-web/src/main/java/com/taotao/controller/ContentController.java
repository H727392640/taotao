package com.taotao.controller;

import com.taotao.commom.utils.TaotaoResult;
import com.taotao.common.pojo.EUDateGridResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * User: H
 * Date: 2019/3/6
 * Time: 16:32
 * Description: No Description
 */
@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    ContentService contentService;

    @RequestMapping("/query/list")
    @ResponseBody
    public EUDateGridResult getConentList(int page, int rows, Long categoryId) {
        return contentService.getContentList(page, rows, categoryId);
    }

    @RequestMapping("/save")
    @ResponseBody
    public TaotaoResult insertContent(TbContent content){
        contentService.insertContent(content);
        return TaotaoResult.ok();
    }

    @RequestMapping("/edit")
    @ResponseBody
    public TaotaoResult editContent(TbContent content){
        contentService.editContent(content);
        return TaotaoResult.ok();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public TaotaoResult deleteContent(Long[] ids){
        contentService.deleteContent(ids);
        return TaotaoResult.ok();
    }

}
