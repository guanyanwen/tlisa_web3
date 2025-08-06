package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.itheima.pro.Dept;
import com.itheima.pro.Result;

import java.util.List;

@Slf4j//开启log
@RestController//作为控制器，病将返回值转化为json
@RequestMapping("depts")
public class DeptController {
    @Autowired
    private DeptService deptService;//创造接口的对象
    @GetMapping//只允许浏览器get请求

    public Result list(){
        List<Dept> list= deptService.list();//调用接口list方法，同list存储
        log.info("查询部门完成");//log记录
        return Result.success(list);//返回状态码，提示，和Result对象
    }
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        deptService.deleteById(id);
        log.info("删除部门{}",id);
        return Result.success();
    }
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept){

        deptService.add(dept);
        log.info("添加完成{}", dept);
        return Result.success();
    }
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept){
        deptService.update(dept);
        log.info("更新完成{}",dept);
        return Result.success(dept);
    }



}
