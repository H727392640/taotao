package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.commom.utils.IDUtils;
import com.taotao.commom.utils.TaotaoResult;
import com.taotao.common.pojo.EUDateGridResult;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemService;
import com.taotao.mapper.TbItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;

    @Override
    public TbItem getItemById(long itemId) {
        //TbItem tbItem = itemMapper.selectByPrimaryKey(itemId);

        TbItemExample tbItemExample = new TbItemExample();
        TbItemExample.Criteria criteria = tbItemExample.createCriteria();
        criteria.andIdEqualTo(itemId);
        List<TbItem> list = itemMapper.selectByExample(tbItemExample);

        if (list != null && list.size() > 0) {
            TbItem tbItem = list.get(0);
            return tbItem;
        }

        return null;
    }

    /**
     * 商品列表查询
     *
     * @param page
     * @param rows
     * @return
     */
    @Override
    public EUDateGridResult getItemList(int page, int rows) {
        TbItemExample example = new TbItemExample();
        PageHelper.startPage(page, rows);

        List<TbItem> list = itemMapper.selectByExample(example);

        EUDateGridResult result = new EUDateGridResult();
        result.setRows(list);

        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());

        return result;
    }

    @Override
    public TaotaoResult createItem(TbItem tbItem, String desc, String itemParams) throws Exception {

        Date date = new Date();
        //生成商品id
        tbItem.setId(IDUtils.genItemId());
        tbItem.setStatus((byte) 1);
        tbItem.setCreated(date);
        tbItem.setUpdated(date);

        itemMapper.insert(tbItem);

        //添加商品描述信息
        TaotaoResult result = insertItemDesc(tbItem.getId(), desc);
        if (result.getStatus() != 200){
            //抛出异常，回归事务， 不能使用try catch进行捕获， 如果进行捕获， 则不会进行回滚
            throw new Exception();
        }

        //添加规格参数
        result = insertItemParam(tbItem.getId(), itemParams);
        if (result.getStatus() != 200){
            //抛出异常，回归事务， 不能使用try catch进行捕获， 如果进行捕获， 则不会进行回滚
            throw new Exception();
        }

        return TaotaoResult.ok();
    }

    private TaotaoResult insertItemParam(long itemId, String itemParam){
        TbItemParamItem tbItemParamItem = new TbItemParamItem();
        Date date = new Date();
        tbItemParamItem.setItemId(itemId);
        tbItemParamItem.setParamData(itemParam);
        tbItemParamItem.setCreated(date);
        tbItemParamItem.setUpdated(date);

        tbItemParamItemMapper.insert(tbItemParamItem);

        return TaotaoResult.ok();
    }

    private TaotaoResult insertItemDesc(long itemId, String desc){
        TbItemDesc tbItemDesc = new TbItemDesc();
        Date date = new Date();

        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setCreated(date);
        tbItemDesc.setUpdated(date);
        tbItemDesc.setItemId(itemId);

        tbItemDescMapper.insert(tbItemDesc);

        return TaotaoResult.ok();
    }
}















