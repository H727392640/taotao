package com.taotao.rest.service;

import com.taotao.commom.utils.TaotaoResult;
import com.taotao.pojo.TbContent;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: H
 * Date: 2019/3/6
 * Time: 22:03
 * Description: No Description
 */
public interface ContentService {
    List<TbContent> getContentList(long contentCid);

}
