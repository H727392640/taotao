package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.commom.utils.HttpClientUtil;
import com.taotao.commom.utils.TaotaoResult;
import com.taotao.common.pojo.EUDateGridResult;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: H
 * Date: 2019/3/6
 * Time: 16:27
 * Description: No Description
 */
@Service
public class ContentServiceImpl implements ContentService {
    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;

    @Value("${REST_CONTENT_SYNC_URL}")
    private String REST_CONTENT_SYNC_URL;

    @Autowired
    TbContentMapper tbContentMapper;

    @Override
    public EUDateGridResult getContentList(int page, int rows, Long categoryId) {
        TbContentExample example = new TbContentExample();

        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        EUDateGridResult result = new EUDateGridResult();

        PageHelper.startPage(page, rows);
        List<TbContent> list = tbContentMapper.selectByExample(example);

        result.setRows(list);

        PageInfo<TbContent> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());

        return result;
    }

    @Override
    public TaotaoResult insertContent(TbContent content) {
        Date date = new Date();
        content.setCreated(date);
        content.setUpdated(date);

        tbContentMapper.insert(content);
        try {
            HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + content.getCategoryId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult editContent(TbContent content) {
        Date date = new Date();

        content.setUpdated(date);

        tbContentMapper.updateByPrimaryKeySelective(content);

        try {
            HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + content.getCategoryId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult deleteContent(Long[] ids) {
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(ids[0]);

        List<TbContent> list = tbContentMapper.selectByExample(example);

        for (Long id : ids){
            tbContentMapper.deleteByPrimaryKey(id);
        }

        try {
            HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + list.get(0).getCategoryId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return TaotaoResult.ok();
    }
}
