package com.example.dao;

import com.example.entity.ClassInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface ClassInfoDao extends Mapper<ClassInfo> {

    @Select("select * from class_info where name like concat('%',#{search},'%')")
    List<ClassInfo> findSearch(@Param("search") String search);

    @Select("select * from class_info where name = #{name} limit 1")
    ClassInfo findByName(@Param("name") String name);
}
