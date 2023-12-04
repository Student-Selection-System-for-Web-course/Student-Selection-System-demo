package com.example.dao;

import com.example.entity.XuankeInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface XuankeInfoDao extends Mapper<XuankeInfo> {
    @Select("select * from xuanke_info where name = #{name} and studentId = #{id} limit 1")
    XuankeInfo find(@Param("name") String name,@Param("id") Long id);

    List<XuankeInfo> findByCondition(@Param("studentId") Long studentId);
}
