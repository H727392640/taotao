package com.taotao.portal.service;

import com.taotao.portal.pojo.SearchResult;

/**
 * Created with IntelliJ IDEA.
 * User: H
 * Date: 2019/3/9
 * Time: 12:29
 * Description: No Description
 */
public interface SearchService {
    SearchResult search(String queryString, int page);
}
