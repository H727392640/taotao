package com.taotao.service;

import com.taotao.common.pojo.EUTreeNode;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: H
 * Date: 2019/3/3
 * Time: 15:08
 * Description: No Description
 */
public interface ItemCatService {
    List<EUTreeNode> getCatList(long parentId);
}
