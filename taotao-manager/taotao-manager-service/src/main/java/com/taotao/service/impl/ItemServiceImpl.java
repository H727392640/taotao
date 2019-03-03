package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.commom.utils.IDUtils;
import com.taotao.commom.utils.TaotaoResult;
import com.taotao.common.pojo.EUDateGridResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
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
    public TaotaoResult createItem(TbItem tbItem) {
        //生成商品id
        tbItem.setId(IDUtils.genItemId());
        tbItem.setStatus((byte) 1);
        tbItem.setCreated(new Date());
        tbItem.setUpdated(new Date());

        itemMapper.insert(tbItem);

        return TaotaoResult.ok();
    }
}















