package com.taotao.rest.service.impl;

import com.taotao.commom.utils.JsonUtils;
import com.taotao.commom.utils.TaotaoResult;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: H
 * Date: 2019/3/9
 * Time: 14:21
 * Description: No Description
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private TbItemDescMapper itemDescMapper;

    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${REDIS_ITEM_KEY}")
    private String REDIS_ITEM_KEY;

    @Value("${REDIS_ITEM_EXPIRE}")
    private Integer REDIS_ITEM_EXPIRE;

    @Override
    public TaotaoResult getItemBaseInfo(long itemId) {

        String key = REDIS_ITEM_KEY + ":" + itemId + ":base";

        try {
            String json = jedisClient.get(key);
            if (!StringUtils.isBlank(json)) {
                TbItem jsonItem = JsonUtils.jsonToPojo(json, TbItem.class);
                return TaotaoResult.ok(jsonItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        TbItem item = itemMapper.selectByPrimaryKey(itemId);

        try {
            jedisClient.set(key, JsonUtils.objectToJson(item));
            jedisClient.expire(key, REDIS_ITEM_EXPIRE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return TaotaoResult.ok(item);
    }

    @Override
    public TaotaoResult getItemDesc(long itemId) {
        String key = REDIS_ITEM_KEY + ":" + itemId + ":desc";

        try {
            String json = jedisClient.get(key);
            if (!StringUtils.isBlank(json)) {
                TbItemDesc jsonItem = JsonUtils.jsonToPojo(json, TbItemDesc.class);
                return TaotaoResult.ok(jsonItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        TbItemDesc item = itemDescMapper.selectByPrimaryKey(itemId);

        try {
            jedisClient.set(key, JsonUtils.objectToJson(item));
            jedisClient.expire(key, REDIS_ITEM_EXPIRE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return TaotaoResult.ok(item);

    }

    @Override
    public TaotaoResult getItemParam(long itemId) {
        String key = REDIS_ITEM_KEY + ":" + itemId + ":param";

        //添加缓存
        try {
            //添加缓存逻辑
            //从缓存中取商品信息，商品id对应的信息
            String json = jedisClient.get(key);
            //判断是否有值
            if (!StringUtils.isBlank(json)) {
                //把json转换成java对象
                TbItemParamItem paramItem = JsonUtils.jsonToPojo(json, TbItemParamItem.class);
                return TaotaoResult.ok(paramItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //根据商品id查询规格参数
        //设置查询条件
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        //执行查询
        List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
        if (list != null && list.size() > 0) {
            TbItemParamItem paramItem = list.get(0);
            try {
                //把商品信息写入缓存
                jedisClient.set(key, JsonUtils.objectToJson(paramItem));
                //设置key的有效期
                jedisClient.expire(key, REDIS_ITEM_EXPIRE);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return TaotaoResult.ok(paramItem);
        }
        return TaotaoResult.build(400, "无此商品规格");
    }
}
















