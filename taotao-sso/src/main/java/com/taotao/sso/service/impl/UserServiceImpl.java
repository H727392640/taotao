package com.taotao.sso.service.impl;

import com.taotao.commom.utils.JsonUtils;
import com.taotao.commom.utils.TaotaoResult;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.sso.dao.JedisClient;
import com.taotao.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: H
 * Date: 2019/3/10
 * Time: 13:35
 * Description: No Description
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TbUserMapper userMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${REDIS_USER_SESSION}")
    private String REDIS_USER_SESSION;


    @Value("${TOKEN_EXPIRE_TIME}")
    private Integer TOKEN_EXPIRE_TIME;

    @Override
    public TaotaoResult checkData(String content, Integer type) {
        //格式如：zhangsan/1  1，其中zhangsan是校验的数据，type为类型，可选参数1、2、3分别代表username、phone、email
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        if (type == 1) {
            //用户名校验
            criteria.andUsernameEqualTo(content);
        } else if (type == 2) {
            criteria.andPhoneEqualTo(content);
        } else {
            criteria.andEmailEqualTo(content);
        }

        List<TbUser> list = userMapper.selectByExample(example);
        if (list == null || list.size() == 0) {
            //数据库中不存在当前数据，可以使用
            return TaotaoResult.ok(true);
        }

        return TaotaoResult.ok(false);
    }


    @Override
    public TaotaoResult createUser(TbUser user) {
        Date date = new Date();
        user.setCreated(date);
        user.setUpdated(date);

        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));

        userMapper.insert(user);

        return TaotaoResult.ok();
    }


    @Override
    public TaotaoResult userLogin(String username, String password) {
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);

        List<TbUser> list = userMapper.selectByExample(example);
        if (list == null || list.size() == 0) {
            return TaotaoResult.build(400, "用户名或密码错误!");
        }

        TbUser user = list.get(0);
        String passwordInSQL = user.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        if (!passwordInSQL.equals(password)) {
            return TaotaoResult.build(400, "用户名或密码错误!");
        }

        //保存用户之前清空密码
        user.setPassword("");
        String[] tokens = UUID.randomUUID().toString().split("-");

        StringBuilder sb = new StringBuilder();
        for (String token : tokens) {
            sb.append(token);
        }
        String token = sb.toString();
        jedisClient.set(REDIS_USER_SESSION + ":" + token, JsonUtils.objectToJson(user));
        jedisClient.expire(REDIS_USER_SESSION + ":" + token, TOKEN_EXPIRE_TIME);

        return TaotaoResult.ok(token);
    }

    @Override
    public TaotaoResult getUserByToken(String token) {

        String json = jedisClient.get(REDIS_USER_SESSION + ":" + token);
        if (StringUtils.isBlank(json)) {
            return TaotaoResult.build(400, "此session已过期,请重新登录");
        }

        //更新过期时间
        jedisClient.expire(REDIS_USER_SESSION + ":" + token, TOKEN_EXPIRE_TIME);

        return TaotaoResult.ok(JsonUtils.jsonToPojo(json, TbUser.class));
    }
}





















