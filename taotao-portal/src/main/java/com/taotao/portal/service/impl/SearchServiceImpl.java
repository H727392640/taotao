package com.taotao.portal.service.impl;

import com.taotao.commom.utils.HttpClientUtil;
import com.taotao.commom.utils.TaotaoResult;
import com.taotao.portal.pojo.SearchResult;
import com.taotao.portal.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: H
 * Date: 2019/3/9
 * Time: 12:31
 * Description: No Description
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Value("${SEARCH_BASE_URL}")
    private String SEARCH_BASE_URL;

    @Override
    public SearchResult search(String queryString, int page) {
        Map<String, String> map = new HashMap<>();
        map.put("q", queryString);
        map.put("page", page + "");
        String json = HttpClientUtil.doGet(SEARCH_BASE_URL, map);

        try{
            TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, SearchResult.class);
            if (taotaoResult.getStatus() == 200){
                SearchResult searchResult = (SearchResult) taotaoResult.getData();
                return searchResult;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }
}
