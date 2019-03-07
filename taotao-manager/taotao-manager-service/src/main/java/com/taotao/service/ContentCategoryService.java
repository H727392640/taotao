package com.taotao.service;

import com.taotao.commom.utils.TaotaoResult;
import com.taotao.common.pojo.EUTreeNode;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: H
 * Date: 2019/3/6
 * Time: 15:05
 * Description: No Description
 */
public interface ContentCategoryService {
    List<EUTreeNode> getCategoryList(Long parentId);
    TaotaoResult insertContentCategory(Long parentId, String name);
    TaotaoResult deleteContentCategory(Long parentId, Long id);
    TaotaoResult updateContentCategory(Long id, String name);
}
