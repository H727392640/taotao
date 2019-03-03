package com.taotao.service.impl;

import com.taotao.commom.utils.FtpUtil;
import com.taotao.commom.utils.IDUtils;
import com.taotao.service.PictureService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: H
 * Date: 2019/3/3
 * Time: 17:16
 * Description: No Description
 */
@Service
public class PictureServiceImpl implements PictureService {


    @Value("${FTP_IP_ADDRESS}")
    private String FTP_IP_ADDRESS;

    @Value("${FTP_PORT}")
    private Integer FTP_PORT;

    @Value("${FTP_USERNAME}")
    private String FTP_USERNAME;

    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;

    @Value("${FTP_BASE_PATH}")
    private String FTP_BASE_PATH;

    @Value("${IMAGE_BASE_URL}")
    private String IMAGE_BASE_URL;

    @Override
    public Map uploadPicture(MultipartFile uploadFile) {
        Map resultMap = new HashMap();
        //生成新的文件名
        String oldName = uploadFile.getOriginalFilename();

        String newName = IDUtils.genImageName() + oldName.substring(oldName.lastIndexOf("."));

        String imagePath = new DateTime().toString("/yyyy/MM/dd");

        try {
            boolean result = FtpUtil.uploadFile(FTP_IP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASE_PATH,
                    imagePath, newName, uploadFile.getInputStream());
            if (!result) {
                resultMap.put("error", 1);
                resultMap.put("message", "文件上传失败");
                return resultMap;
            }
            resultMap.put("error", 0);
            resultMap.put("url", IMAGE_BASE_URL + imagePath + "/" + newName);
            return resultMap;
        } catch (IOException e) {
            resultMap.put("error", 1);
            resultMap.put("message", "文件上传失败");
            return resultMap;
        }

    }
}
