package com.taotao.portal.service;

import com.taotao.commom.utils.TaotaoResult;
import com.taotao.portal.pojo.CartItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: H
 * Date: 2019/3/11
 * Time: 15:22
 * Description: No Description
 */
public interface CartService {
    TaotaoResult addCartItem(long itemId, int number, HttpServletRequest request, HttpServletResponse response);

    List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response);

    TaotaoResult deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response);
}
