package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import com.itheima.pro.Emp;
import com.itheima.pro.PageBean;
import com.itheima.pro.Result;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
public class EmpController {
    @Autowired
    private EmpService empService;

    @GetMapping("/emps")
    public Result selectemps(@RequestParam(defaultValue = "1") Integer page/* 一定要与前端的一样才能接受*/,
                             @RequestParam(defaultValue = "10") Integer pageSize,
                             String name, Short gender,
                             @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                             @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end
    ) {
        PageBean bean = empService.selectemps(page, pageSize, name, gender, begin, end);
        log.info("分页结果{},{},{},{},{},{}", page, pageSize, name, gender, begin, end);
        return Result.success(bean);
    }

    //删除员工
    @Log
    @DeleteMapping("/emps/{ids}")
    public Result delect(@PathVariable/*提取路径里的值*/ List<Integer> ids) {
        empService.delect(ids);
        log.info("删除了{}", ids);
        return Result.success();
    }
    //添加员工
    @Log
    @PostMapping("/emps")
    public Result insert(@RequestBody/*自动提取请求体东西，给与emp*/ Emp emp){
        empService.insert(emp);
        log.info("添加{}",emp);
        return Result.success();

    }
    @GetMapping("/emps/{id}")
    public  Result selectbyid(@PathVariable Integer id){
        Emp emp=empService.selectbyid(id);
        return Result.success(emp);
    }

    //修改数据
    @Log
    @PutMapping("/emps")
    public  Result update(@RequestBody Emp emp){
        empService.update(emp);
        return Result.success();
    }

}

