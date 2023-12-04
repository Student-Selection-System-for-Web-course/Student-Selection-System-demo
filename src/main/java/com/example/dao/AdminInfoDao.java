package com.example.dao;

import com.example.entity.AdminInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

//注解表示将dao层作为一个bean
@Repository
public interface AdminInfoDao extends Mapper<AdminInfo> {//Mapper意味着将该类和AdminInfo实体类绑定，可直接操作数据库

	//@Param("name")表示如果参数多于一个需要添加这个
	@Select("select * from admin_info where name = #{name} and password = #{password}")
	//表示查询的一个注解
	AdminInfo findByNameAndPass(@Param("name") String name, @Param("password") String password);

	@Select("select * from admin_info where name = #{name}")
	AdminInfo findByName(@Param("name")String name);

	@Select("select * from admin_info where name like concat('%',#{name},'%')")
	List<AdminInfo> findByNamePage(@Param("name")String name);
}
