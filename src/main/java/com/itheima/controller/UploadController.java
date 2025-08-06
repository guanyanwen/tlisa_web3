package com.itheima.controller;
import java.io.File;
import java.io.IOException;
import java.util.*;

import com.itheima.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.itheima.pro.Result;

@Slf4j
@RestController
public class UploadController {
    //本地存储
//    @PostMapping("/upload")
//    public Result upload(String username, Integer age, /*文件对象类型*/MultipartFile image) throws Exception {
//        //创造独特的文件名
//        int i=image.getOriginalFilename().lastIndexOf(".");
//        String name=UUID.randomUUID().toString()+image.getOriginalFilename().substring(i);
//        //唱见文件并存入
//        image.transferTo(new File("C:/Users/gyw/Desktop/bin/"+name));
//        log.info("上传成功{}，{}，{}",username,age,image);
//        return Result.success();
//    }

    //阿里云存储
    @Autowired
    private AliOSSUtils aliOSSUtils;
    @PostMapping("/upload")//有文件上传，调用以下函数
    public Result upload(MultipartFile image/*读取文件*/) throws Exception {
        log.info("上传{}",image.getOriginalFilename());//提取名字
        String url = aliOSSUtils.upload(image);//上传云端，并返回一个云端图片的url
        log.info("文件的url{}",url);
        return Result.success(url);



    }
}
