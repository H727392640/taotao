package com.taotao.search.service;

import com.taotao.search.pojo.SearchResult;

/**
 * Created with IntelliJ IDEA.
 * User: H
 * Date: 2019/3/8
 * Time: 16:28
 * Description: No Description
 */
public interface SearchService {
    SearchResult search(String queryString, int page, int rows) throws Exception;
}
