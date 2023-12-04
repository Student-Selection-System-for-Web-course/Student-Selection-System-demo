package com.example.service;


import cn.hutool.core.util.ObjectUtil;
import com.example.common.ResultCode;
import com.example.dao.AdminInfoDao;
import com.example.entity.Account;
import com.example.entity.AdminInfo;
import com.example.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

//注解作为一个bean交给Spring管理
@Service
public class AdminInfoService {
	//需要使用Dao层中的东西，则需要将Dao层引用进来
	@Resource
	private AdminInfoDao adminInfoDao;

	public Account login(String name, String password) {
		//通过用户名和密码去数据库中查询
		AdminInfo adminInfo = adminInfoDao.findByNameAndPass(name,password);

		//判断是否为空
		if(ObjectUtil.isEmpty(adminInfo)){
			//为空则返回一个提示，拿到信息后返回到Controller
			throw new CustomException("-1","用户名、密码或角色错误");
		}
		return adminInfo;
	}

	public Account findById(Long id) {
		return adminInfoDao.selectByPrimaryKey(id);//根据主键查询
	}

	public void update(AdminInfo adminInfo) {
		adminInfoDao.updateByPrimaryKeySelective(adminInfo);
	}

	public void add(AdminInfo adminInfo) {
		//能否直接插入数据库呢？
		//不能直接插入，1. 因为新增的时候没有密码，需要初始化 2. 若新增同名管理员，无法实行，存在bug，需要校验

		//1. 查询是否有同名
		AdminInfo info = adminInfoDao.findByName(adminInfo.getName());
		if(ObjectUtil.isNotEmpty(info)){
			//如果查到，提示已存在
			throw new CustomException(ResultCode.USER_EXIST_ERROR);
		}
		if(ObjectUtil.isEmpty(adminInfo.getPassword())){
			//没有密码的时候初始化一个初始密码
			adminInfo.setPassword("123456");
		}
		adminInfoDao.insertSelective(adminInfo);
	}

	public List<AdminInfo> findAll() {
		return adminInfoDao.selectAll();
	}

	public void deleteById(Long id) {
		adminInfoDao.deleteByPrimaryKey(id);
	}

	public PageInfo<AdminInfo> findPage(Integer pageNum, Integer pageSize) {
		//开启分页
		PageHelper.startPage(pageNum,pageSize);
		//下面查询会自动根据pageNum和pageSize来查询对应数据
		List<AdminInfo> infos = adminInfoDao.selectAll();
		return PageInfo.of(infos);
	}

	public PageInfo<AdminInfo> findPageName(Integer pageNum, Integer pageSize, String name) {
		//开启分页
		PageHelper.startPage(pageNum,pageSize);
		List<AdminInfo> infos = adminInfoDao.findByNamePage(name);
		return PageInfo.of(infos);
	}
}

