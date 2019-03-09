package com.taotao.portal.controller;

import com.taotao.portal.pojo.SearchResult;
import com.taotao.portal.service.SearchService;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created with IntelliJ IDEA.
 * User: H
 * Date: 2019/3/9
 * Time: 12:37
 * Description: No Description
 */

@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;

    @RequestMapping("/search")
    public String search(@RequestParam("q") String queryString, @RequestParam(defaultValue = "1") Integer page, Model model){

        if (queryString != null){
            try {
                queryString = new String(queryString.getBytes("iso8859-1"), "utf-8");
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        SearchResult searchResult =searchService.search(queryString, page);
        model.addAttribute("query", queryString);
        model.addAttribute("totalPages", searchResult.getPageCount());
        model.addAttribute("itemList", searchResult.getItemList());
        model.addAttribute("page", page);
        return "search";
    }
}
