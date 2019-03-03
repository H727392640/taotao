package com.taotao.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: H
 * Date: 2019/3/3
 * Time: 17:15
 * Description: No Description
 */
public interface PictureService {
    Map uploadPicture(MultipartFile uploadFile);
}
