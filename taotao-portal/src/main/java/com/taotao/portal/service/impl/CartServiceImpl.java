package com.taotao.portal.service.impl;

import com.taotao.commom.utils.CookieUtils;
import com.taotao.commom.utils.HttpClientUtil;
import com.taotao.commom.utils.JsonUtils;
import com.taotao.commom.utils.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.CartService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: H
 * Date: 2019/3/11
 * Time: 15:23
 * Description: No Description
 */
@Service
public class CartServiceImpl implements CartService {
    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;

    @Value("${ITEM_INFO_URL}")
    private String ITEM_INFO_URL;

    @Override
    public TaotaoResult addCartItem(long itemId, int number, HttpServletRequest request, HttpServletResponse response) {

        List<CartItem> itemList = getCartItemList(request);

        CartItem cartItem = null;

        for (CartItem cItem : itemList) {
            //如果存在商品
            if (cItem.getId() == itemId) {
                cItem.setNum(cItem.getNum() + number);
                cartItem = cItem;
                break;
            }
        }

        if (cartItem == null) {
            cartItem = new CartItem();
            String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO_URL + itemId);
            TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbItem.class);

            if (taotaoResult.getStatus() == 200) {
                TbItem item = (TbItem) taotaoResult.getData();
                cartItem.setId(item.getId());
                cartItem.setTitle(item.getTitle());
                cartItem.setNum(number);
                cartItem.setPrice(item.getPrice());
                cartItem.setImage(item.getImage() == null ? "" : item.getImage().split(",")[0]);
            }
            itemList.add(cartItem);
        }

        //重新写回Cookie
        CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(itemList), true);

        return TaotaoResult.ok();
    }

    private List<CartItem> getCartItemList(HttpServletRequest request) {
        String cartJson = CookieUtils.getCookieValue(request, "TT_CART", true);
        if (cartJson == null) {
            return new ArrayList<>();
        }
        try {
            return JsonUtils.jsonToList(cartJson, CartItem.class);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response) {
        return getCartItemList(request);
    }

    @Override
    public TaotaoResult deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response) {

        List<CartItem> itemList = getCartItemList(request);
        for (CartItem cartItem : itemList){
            if (cartItem.getId() == itemId){
                itemList.remove(cartItem);
                break;
            }
        }

        CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(itemList), true);
        return TaotaoResult.ok();
    }
}
