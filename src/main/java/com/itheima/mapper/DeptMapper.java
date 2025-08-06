package com.itheima.mapper;

import org.apache.ibatis.annotations.*;
import com.itheima.pro.Dept;

import java.util.List;

@Mapper//自动扫描并生成该接口的实现类，从而实现接口与 SQL 语句的绑定，也会收入bean
public interface DeptMapper {
    @Select("select * from dept")//输入sql命令
    List<Dept> list();//因为自动实现类，所以这里抽象方法，规定bean里存什么样式

    @Delete("delete  from dept where id=#{id}")
    void deleteById(Integer id);
    @Insert("INSERT into dept(name,create_time,update_time) values (#{name},#{createTime},#{updateTime})")
    void add(Dept dept);
    @Update("update dept set name=#{name},update_time=#{updateTime} where id=#{id}")
    void update(Dept dept);
    @Select("select * from dept where id=#{id}")
    Dept selectone(Integer id);
}
