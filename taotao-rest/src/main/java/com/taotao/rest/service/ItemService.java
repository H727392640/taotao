package com.taotao.rest.service;

import com.taotao.commom.utils.TaotaoResult;

/**
 * Created with IntelliJ IDEA.
 * User: H
 * Date: 2019/3/9
 * Time: 14:20
 * Description: No Description
 */
public interface ItemService {
    TaotaoResult getItemBaseInfo(long itemId);
    TaotaoResult getItemDesc(long itemId);
    TaotaoResult getItemParam(long itemId);
}
