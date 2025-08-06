package com.itheima.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix ="aliossutils.oss")
public class AliOSSUtilsPro {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;


}
