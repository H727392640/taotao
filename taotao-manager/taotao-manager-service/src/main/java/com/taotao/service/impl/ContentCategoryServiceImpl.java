package com.taotao.service.impl;

import com.taotao.commom.utils.TaotaoResult;
import com.taotao.common.pojo.EUTreeNode;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: H
 * Date: 2019/3/6
 * Time: 15:06
 * Description: No Description
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;

    @Override
    public List<EUTreeNode> getCategoryList(Long parentId) {
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();

        criteria.andParentIdEqualTo(parentId);

        List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
        List<EUTreeNode> resultList = new ArrayList<>();
        for (TbContentCategory tbContentCategory : list) {
            EUTreeNode euTreeNode = new EUTreeNode();
            euTreeNode.setId(tbContentCategory.getId());
            euTreeNode.setText(tbContentCategory.getName());
            euTreeNode.setState(tbContentCategory.getIsParent() ? "closed" : "open");
            resultList.add(euTreeNode);
        }

        return resultList;
    }


    @Override
    public TaotaoResult insertContentCategory(Long parentId, String name) {
        TbContentCategory insertCategory = new TbContentCategory();
        TbContentCategory parentCategory = contentCategoryMapper.selectByPrimaryKey(parentId);
        Date date = new Date();

        insertCategory.setName(name);
        insertCategory.setCreated(date);
        insertCategory.setUpdated(date);
        insertCategory.setParentId(parentId);
        insertCategory.setIsParent(false);
        insertCategory.setSortOrder(1);
        insertCategory.setStatus(1);
        contentCategoryMapper.insert(insertCategory);


        if (!parentCategory.getIsParent()) {
            parentCategory.setUpdated(date);
            parentCategory.setIsParent(true);
            contentCategoryMapper.updateByPrimaryKey(parentCategory);
        }
        return TaotaoResult.ok(insertCategory);
    }

    @Override
    public TaotaoResult deleteContentCategory(Long parentId, Long id) {
        //获取选中节点
        TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
        //删除当前节点对应的内容
        contentCategoryMapper.deleteByPrimaryKey(id);

        //判断选中节点是否为父节点，如果是父节点，则删除parenrId = id的记录
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(id);
        List<TbContentCategory> childList = contentCategoryMapper.selectByExample(example);
        if (childList != null && childList.size() > 0){
            contentCategoryMapper.deleteByExample(example);
        }

        //判断删除当前节点之后，父节点是否仍未父节点，如果不是，则修改状态
        TbContentCategory parentContentCategory = contentCategoryMapper.selectByPrimaryKey(parentId);
        TbContentCategoryExample parentExample =  new TbContentCategoryExample();
        TbContentCategoryExample.Criteria parentCriteria = parentExample.createCriteria();
        List<TbContentCategory> parentList = contentCategoryMapper.selectByExample(parentExample);
        if (parentList == null || parentList.size() == 0){
            parentContentCategory.setIsParent(false);
            contentCategoryMapper.updateByPrimaryKey(parentContentCategory);
        }

        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult updateContentCategory(Long id, String name) {
        TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
        Date date = new Date();
        contentCategory.setName(name);
        contentCategory.setUpdated(date);

        contentCategoryMapper.updateByPrimaryKey(contentCategory);
        return TaotaoResult.ok();
    }

}
