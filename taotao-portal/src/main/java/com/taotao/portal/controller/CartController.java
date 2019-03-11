package com.taotao.portal.controller;

import com.taotao.commom.utils.TaotaoResult;
import com.taotao.portal.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: H
 * Date: 2019/3/11
 * Time: 15:48
 * Description: No Description
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping("/item/{itemId}")
    public String addCartItem(@PathVariable Long itemId, @RequestParam(defaultValue = "1") Integer number, HttpServletRequest request, HttpServletResponse response){
        cartService.addCartItem(itemId, number, request, response);
        return "cartSuccess";
    }
}
