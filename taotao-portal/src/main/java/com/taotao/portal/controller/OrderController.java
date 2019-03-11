package com.taotao.portal.controller;

import com.sun.java.swing.plaf.motif.MotifEditorPaneUI;
import com.taotao.commom.utils.ExceptionUtil;
import com.taotao.commom.utils.TaotaoResult;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.pojo.Order;
import com.taotao.portal.service.CartService;
import com.taotao.portal.service.OrderService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: H
 * Date: 2019/3/11
 * Time: 22:13
 * Description: No Description
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;

    @RequestMapping("/order-cart")
    public String showOrderCart(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<CartItem> cartList = cartService.getCartItemList(request, response);
        model.addAttribute("cartList", cartList);
        return "order-cart";
    }

    @RequestMapping("/create")
    public String createOrder(Order order, Model model) {
        try{
            String orderId = orderService.createOrder(order);
            model.addAttribute("orderId", orderId);
            model.addAttribute("payment", order.getPayment());
            model.addAttribute("date", new DateTime().plusDays(3).toString("yyyy-MM-dd"));
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("message", "创建订单出错，请稍后重试！");
            return "error/exception";
        }

    }
}
