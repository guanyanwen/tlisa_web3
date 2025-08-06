package com.itheima.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;
import com.itheima.pro.Emp;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
//    @Select("select  count(*) from emp")
//    public int count();
//
//    @Select("select * from emp limit #{num},#{num_per}")
//    public List<Emp> selectemp(@Param("num") Integer num,@Param("num_per") Integer num_per);

//        @Select("select  * from emp where name=#{name} and gender=#{gender} and entrydate between #{begin} and #{end}")
        List<Emp> selectemp2( @Param("name") String name,
                              @Param("gender") Short gender,
                              @Param("begin") LocalDate begin,
                              @Param("end") LocalDate end);
        void delect(@Param("ids") List<Integer> ids);

        @Insert("insert into emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
                "values (#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
        void insert(Emp emp);

        @Select("select * from emp where id=#{id}")
        Emp selectbyid(Integer id);

        void update(Emp emp);

        @Select("select * from emp where username=#{username} and password=#{password}")
        Emp loginbynameId(Emp emp);

        @Delete("delete from  emp where dept_id=#{deptId}")
        void deletebyEmpid(Integer deptId);
}

