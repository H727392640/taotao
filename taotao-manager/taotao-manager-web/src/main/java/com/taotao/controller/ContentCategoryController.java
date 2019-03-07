package com.taotao.controller;

import com.taotao.commom.utils.TaotaoResult;
import com.taotao.common.pojo.EUDateGridResult;
import com.taotao.common.pojo.EUTreeNode;
import com.taotao.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: H
 * Date: 2019/3/6
 * Time: 15:11
 * Description: No Description
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

    @Autowired
    ContentCategoryService contentCategoryService;

    @RequestMapping("/list")
    @ResponseBody
    public List<EUTreeNode> getContentCategoryList(@RequestParam(value = "id", defaultValue = "0") long parentId){
        return contentCategoryService.getCategoryList(parentId);
    }

    @RequestMapping("/create")
    @ResponseBody
    public TaotaoResult createContentCategory(Long parentId, String name){
        TaotaoResult result = contentCategoryService.insertContentCategory(parentId, name);
        return TaotaoResult.ok(result);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public TaotaoResult deleteContentCategory(Long parentId, Long id){
        TaotaoResult result = contentCategoryService.deleteContentCategory(parentId, id);
        return TaotaoResult.ok();
    }

    @RequestMapping("/update")
    @ResponseBody
    public TaotaoResult deleteContentCategory(Long id, String name){
        TaotaoResult result = contentCategoryService.updateContentCategory(id, name);
        return TaotaoResult.ok();
    }

}
