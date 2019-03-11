package com.taotao.portal.service;

import com.taotao.pojo.TbUser;

/**
 * Created with IntelliJ IDEA.
 * User: H
 * Date: 2019/3/11
 * Time: 14:34
 * Description: No Description
 */
public interface UserService {
    TbUser getUserByToken(String token);
}
