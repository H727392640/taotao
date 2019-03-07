package com.taotao.service;

import com.taotao.commom.utils.TaotaoResult;
import com.taotao.common.pojo.EUDateGridResult;
import com.taotao.pojo.TbItemParam;

/**
 * Created with IntelliJ IDEA.
 * User: H
 * Date: 2019/3/4
 * Time: 20:42
 * Description: No Description
 */
public interface ItemParamService {
    TaotaoResult getItemParamByCid(long cid);

    TaotaoResult insertItemParam(TbItemParam tbItemParam);

    EUDateGridResult getItemParamList(int page, int rows);
}
