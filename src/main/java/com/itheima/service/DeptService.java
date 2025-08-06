package com.itheima.service;

import com.itheima.pro.Dept;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

public interface DeptService {

    List<Dept> list();

    void deleteById(Integer id);

    void add(Dept dept);

    void update(Dept dept);
}
