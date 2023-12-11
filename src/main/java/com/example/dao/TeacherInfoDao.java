package com.example.dao;


import com.example.entity.TeacherInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


//注解表示将dao层作为一个bean
@Repository
public interface TeacherInfoDao extends Mapper<TeacherInfo> {
    @Select("select * from teacher_info where name = #{name}")
    TeacherInfo findByName(@Param("name") String name);//Mapper意味着将该类和AdminInfo实体类绑定，可直接操作数据库

    @Select(("select * from teacher_info where  name = #{name} and password = #{password}"))
    TeacherInfo findByNameAndPass(@Param("name") String name, @Param("password") String password);


    @Select("select * from teacher_info where name like concat('%',#{search},'%')")
    List<TeacherInfo> findByLikeName(@Param("search") String search);
}
