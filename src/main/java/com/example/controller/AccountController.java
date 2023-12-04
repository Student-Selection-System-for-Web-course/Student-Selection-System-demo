package com.example.controller;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.Result;
import com.example.entity.Account;
import com.example.entity.AdminInfo;
import com.example.service.AdminInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping
public class AccountController {

	//引入Service层
	@Resource
	AdminInfoService adminInfoService;

	@PostMapping("/login")
	//Result是自己定义的一个统一返回给前端的数据结构
	public Result login(@RequestBody Account user, HttpServletRequest request){

		//校验数据有没有填
		//判断是否为空，存在为空的情况下
		if(ObjectUtil.isEmpty(user.getName()) || ObjectUtil.isEmpty(user.getPassword()) || ObjectUtil.isEmpty(user.getLevel())){
			return Result.error("-1","请完善输入信息");
		}

		//不为空时，进行登录
		Integer level = user.getLevel();//获取level
		Account loginUser=new Account();//定义一个Account类用来接收

		if(1==level){
			//管理员登录
			loginUser= adminInfoService.login(user.getName(), user.getPassword());

		}
		if(2==level){
			//教师登录
		}
		if(3==level){
			//学生登录
		}

		//在session里面把用户信息存一遍
		request.getSession().setAttribute("user",loginUser);

		return Result.success(loginUser);
	}

	@GetMapping("/getUser")//表示一个get请求
	public Result getUser(HttpServletRequest request){
		//先从session里获取当前所存的已登录的用户信息
		Account user = (Account)request.getSession().getAttribute("user");
		//判断当前用户为什么角色
		Integer level = user.getLevel();
		if(1==level){
			//获取管理员信息
			AdminInfo adminInfo= (AdminInfo) adminInfoService.findById(user.getId());
			return Result.success(adminInfo);

		}
		if(2==level){
			//获取教师信息
		}
		if(3==level){
			//获取学生信息
		}
		return Result.success(new Account());

	}

}
