package com.taotao.sso.service;

import com.taotao.commom.utils.TaotaoResult;
import com.taotao.pojo.TbUser;

/**
 * Created with IntelliJ IDEA.
 * User: H
 * Date: 2019/3/10
 * Time: 13:34
 * Description: No Description
 */
public interface UserService {

    TaotaoResult checkData(String content, Integer type);

    TaotaoResult createUser(TbUser user);

    TaotaoResult userLogin(String username, String password);

    TaotaoResult getUserByToken(String token);

}
