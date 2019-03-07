package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.commom.utils.TaotaoResult;
import com.taotao.common.pojo.EUDateGridResult;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExample.Criteria;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: H
 * Date: 2019/3/4
 * Time: 20:43
 * Description: No Description
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Autowired
    TbItemParamMapper tbItemParamMapper;

    @Override
    public TaotaoResult getItemParamByCid(long cid) {
        TbItemParamExample tbItemParamExample = new TbItemParamExample();
        Criteria criteria = tbItemParamExample.createCriteria();

        criteria.andItemCatIdEqualTo(cid);
        //包含大文本列
        List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(tbItemParamExample);

        if (list != null && list.size() > 0){
            return TaotaoResult.ok(list.get(0));
        }

        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult insertItemParam(TbItemParam tbItemParam) {
        Date date = new Date();
        tbItemParam.setCreated(date);
        tbItemParam.setUpdated(date);
        tbItemParamMapper.insert(tbItemParam);
        return TaotaoResult.ok();
    }

    @Override
    public EUDateGridResult getItemParamList(int page, int rows) {
        TbItemParamExample tbItemParamExample = new TbItemParamExample();

        PageHelper.startPage(page, rows);
        List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(tbItemParamExample);

        EUDateGridResult result = new EUDateGridResult();
        result.setRows(list);

        PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
}
