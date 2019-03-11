package com.taotao.portal.service;

import com.taotao.commom.utils.TaotaoResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: H
 * Date: 2019/3/11
 * Time: 15:22
 * Description: No Description
 */
public interface CartService {
    TaotaoResult addCartItem(long itemId, int number, HttpServletRequest request, HttpServletResponse response);
}
