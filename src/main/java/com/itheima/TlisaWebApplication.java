package com.itheima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan//使用filter
@SpringBootApplication
public class TlisaWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(TlisaWebApplication.class, args);
    }

}
