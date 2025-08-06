package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpMapper;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.itheima.pro.Emp;
import com.itheima.pro.PageBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

//    @Override
//    public PageBean selectemps(Integer num, Integer numPer) {
//        Integer start = (num - 1) * numPer;
//        List<Emp> list = empMapper.selectemp(start, numPer);
//        Integer count = empMapper.count();
//        PageBean pagebean = new PageBean(count, list);
//        return pagebean;
//    }


    @Override
    public PageBean selectemps(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
        PageHelper.startPage(page,pageSize);
        List<Emp> emps = empMapper.selectemp2(name,gender,begin,end);
        Page<Emp> emps_new = (Page<Emp>) emps;
        PageBean pageBean = new PageBean((int) emps_new.getTotal(),emps_new.getResult());
        return pageBean;

    }

    @Override
    public void delect(List<Integer> ids) {
        empMapper.delect(ids);
    }

    @Override
    public void insert(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }

    @Override
    public Emp selectbyid(Integer id) {
        return empMapper.selectbyid(id);
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.loginbynameId(emp);

    }
}
