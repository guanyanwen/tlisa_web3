package com.itheima.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.UUID;

/**
 * 阿里云 OSS 工具类
 */
@Component

public class AliOSSUtils {

//    private String endpoint = "https://oss-cn-shanghai.aliyuncs.com";
//    private String accessKeyId = "LTAI5tQjH9QFn3SF8pbk7ZMN";
//    private String accessKeySecret = "5ihmO3gM1F4644EegOXMI5p04mBqzr";
//    private String bucketName = "web-tlias-bygyw";
@Autowired
private AliOSSUtilsPro aliOSSUtilsPro;


    public String upload(MultipartFile file) throws IOException {
        String endpoint= aliOSSUtilsPro.getEndpoint();
        String accessKeyId=aliOSSUtilsPro.getAccessKeyId();
        String accessKeySecret= aliOSSUtilsPro.getAccessKeySecret();
        String bucketName=aliOSSUtilsPro.getBucketName();

        // 获取上传的文件的输入流
        InputStream inputStream = file.getInputStream();

        // 避免文件覆盖
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));

        //上传文件到 OSS
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName, fileName, inputStream);

        //文件访问路径
        String url = endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + fileName;
        // 关闭ossClient
        ossClient.shutdown();
        return url;// 把上传到oss的路径返回
    }

}
