package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.Result;
import com.example.common.ResultCode;
import com.example.dao.TeacherInfoDao;
import com.example.entity.Account;
import com.example.entity.AdminInfo;
import com.example.entity.TeacherInfo;
import com.example.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


//注解作为一个bean交给Spring管理
@Service
public class TeacherInfoService {

	@Resource
	private TeacherInfoDao teacherInfoDao;

	public Account login(String name, String password) {
		//通过用户名和密码去数据库中查询
		TeacherInfo teacherInfo = teacherInfoDao.findByNameAndPass(name, password);

		//判断是否为空
		if(ObjectUtil.isEmpty(teacherInfo)){
			//为空则返回一个提示，拿到信息后返回到Controller
			throw new CustomException("-1","用户名、密码或角色错误");
		}
		return teacherInfo;
	}

	public TeacherInfo findById(Long id) {
		return teacherInfoDao.selectByPrimaryKey(id);
	}

	public void update(TeacherInfo teacherInfo) {
		teacherInfoDao.updateByPrimaryKeySelective(teacherInfo);
	}

	public List<TeacherInfo> findAll() {
		return teacherInfoDao.selectAll();
	}

	public void add(TeacherInfo teacherInfo) {
		//1.检测数据库中是否已有同名用户，若有同名用户，提示重新输入
		TeacherInfo info = teacherInfoDao.findByName(teacherInfo.getName());
		if(ObjectUtil.isNotEmpty(info)){
			//已有用户
			throw new CustomException(ResultCode.USER_EXIST_ERROR);
		}
		if(ObjectUtil.isNotEmpty(teacherInfo.getPassword())){
			teacherInfo.setPassword("123456");
		}
		teacherInfo.setLevel(2);
		teacherInfoDao.insertSelective(teacherInfo);

	}

	public void deleteById(Long id) {
		teacherInfoDao.deleteByPrimaryKey(id);
	}

	public PageInfo<TeacherInfo> findPage(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<TeacherInfo> list = teacherInfoDao.selectAll();
		return PageInfo.of(list);
	}

	public PageInfo<TeacherInfo> findPageSearch(String search, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		List<TeacherInfo> teacherInfos = teacherInfoDao.findByLikeName(search);
		return PageInfo.of(teacherInfos);
	}
}

