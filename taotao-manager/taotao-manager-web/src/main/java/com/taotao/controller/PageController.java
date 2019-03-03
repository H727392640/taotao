package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: H
 * Date: 2019/3/3
 * Time: 13:16
 * Description: 页面跳转Controller
 */
@Controller
public class PageController {

    /**
     * 打开首页
     * @return
     */
    @RequestMapping({"/", "/index"})
    public String showIndexPage(){
        return "index";
    }

    /**
     * 展示其它页面
     * @param page
     * @return
     */
    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page){
        return page;
    }
}
