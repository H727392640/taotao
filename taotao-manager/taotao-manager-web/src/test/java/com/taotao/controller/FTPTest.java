package com.taotao.controller;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: H
 * Date: 2019/3/3
 * Time: 16:32
 * Description: No Description
 */
public class FTPTest {
    @Test
    public void testFtpClient() throws IOException {
        /*
         * 具体原因是：在安装有 IPv6 和 IPv4 的计算机上，
         * 会使用一种 IPv6 模拟的 IPv4(The only difference between JDK7 and older releases
         * is that the JDK is using IPv6 sockets when IPv6 is enabled and so IPv4-mapped
         * IPv6 addresses are used)，而 windows 防火墙会把这种模拟的 IPv4 数据挡住。
         */
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect("192.168.180.129", 21);

        ftpClient.login("ftpuser", "ftpuser");

        FileInputStream fileInputStream = new FileInputStream(new File("C:/Users/H/Desktop/test.jpg"));

        ftpClient.changeWorkingDirectory("/home/ftpuser/www/images/");
        //修改上传文件格式
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

        //第一个参数，服务端文档名
        //第二个参数，上传文档的inputStream
        ftpClient.storeFile("hello.jpg", fileInputStream);

        ftpClient.logout();

    }
}
