package com.taotao.portal.service;

import com.taotao.portal.pojo.ItemInfo;

/**
 * Created with IntelliJ IDEA.
 * User: H
 * Date: 2019/3/9
 * Time: 15:18
 * Description: No Description
 */
public interface ItemService {
    ItemInfo getItemById(Long itemId);
    String getItemDescById(Long itemId);
    String getItemParamById(Long itemId);

}
