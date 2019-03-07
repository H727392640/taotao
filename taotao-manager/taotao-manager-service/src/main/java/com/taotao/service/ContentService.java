package com.taotao.service;

import com.taotao.commom.utils.TaotaoResult;
import com.taotao.common.pojo.EUDateGridResult;
import com.taotao.pojo.TbContent;

/**
 * Created with IntelliJ IDEA.
 * User: H
 * Date: 2019/3/6
 * Time: 16:23
 * Description: No Description
 */
public interface ContentService {
    EUDateGridResult getContentList(int page, int rows, Long categoryId );

    TaotaoResult insertContent(TbContent content);
}
