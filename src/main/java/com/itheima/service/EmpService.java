package com.itheima.service;

import com.itheima.pro.Emp;
import com.itheima.pro.PageBean;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {

    PageBean selectemps(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);
    void delect(List<Integer> ids);

    void insert(Emp emp);

    Emp selectbyid(Integer id);

    void update(Emp emp);

    Emp login(Emp emp);
}
