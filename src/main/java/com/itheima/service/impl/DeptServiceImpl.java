package com.itheima.service.impl;

import com.itheima.mapper.DeptMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.itheima.pro.Dept;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service//纳入容器管理，会让control那边接收到bean
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;//为接口mapper创造实例,病自动接收bean里的东西
    @Autowired
    private EmpMapper empMapper;
    @Override
    public List<Dept>  list(){
        return deptMapper.list();//用@Mapper自动类创造的list（）将sql里的表转化为list
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(Integer id) {
        deptMapper.deleteById(id);
        empMapper.deletebyEmpid(id);
    }
    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.add(dept);

    }

    @Override
    public void update(Dept dept) {
        LocalDateTime oldTime=deptMapper.selectone(dept.getId()).getCreateTime();
        dept.setUpdateTime(LocalDateTime.now());
        dept.setCreateTime(oldTime);
        deptMapper.update(dept);
    }
}
