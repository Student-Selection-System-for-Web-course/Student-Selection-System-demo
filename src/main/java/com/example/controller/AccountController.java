package com.example.controller;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.Result;
import com.example.entity.Account;
import com.example.entity.AdminInfo;
import com.example.entity.StudentInfo;
import com.example.service.AdminInfoService;
import com.example.service.StudentInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping
public class AccountController {
    @Resource
    AdminInfoService adminInfoService;
    @Resource
    StudentInfoService studentInfoService;

    @PostMapping("/login")
    public Result login(@RequestBody Account user, HttpServletRequest request) {
        if (ObjectUtils.isEmpty(user.getLevel()) || ObjectUtils.isEmpty(user.getName()) || ObjectUtils.isEmpty(user.getPassword()))
            return Result.error("-1", "填的不全");
        Integer level = user.getLevel();

        Account loginUser = new Account();

        if (level == 1) {
            //管理员登录
            loginUser = adminInfoService.login(user.getName(), user.getPassword());
        }

        if (level == 2) {
            //教师登录
        }

        if (level == 3) {
            //学生登录
            loginUser = studentInfoService.login(user.getName() ,user.getPassword());
        }

        request.getSession().setAttribute("user",loginUser);
        return Result.success(loginUser);
    }

    @PostMapping("/register")
    public Result register(@RequestBody Account user, HttpServletRequest request){
        if(ObjectUtil.isEmpty(user.getName()) || ObjectUtil.isEmpty(user.getPassword()) || ObjectUtil.isEmpty(user.getLevel())){
            return Result.error("-1","请输入完整信息");
        }

        Integer level = user.getLevel();
        //学生注册
        if(level == 3){
            StudentInfo studentInfo = new StudentInfo();
            BeanUtils.copyProperties(user,studentInfo);
            studentInfoService.register(studentInfo);
        }
        return Result.success();
    }

    @GetMapping("/getUser")
    public Result getUser(HttpServletRequest request){
        //先从session里面获取当前存的登录用户信息
        Account user = (Account) request.getSession().getAttribute("user");
        //判断用户角色
        Integer level = user.getLevel();
        if (level == 1) {
            //获取管理员信息
            AdminInfo adminInfo = adminInfoService.findById(user.getId());
            return Result.success(adminInfo);
        }

        if (level == 2) {
            //获取教师信息
        }

        if (level == 3) {
            //获取学生信息
            StudentInfo studentInfo = studentInfoService.findById(user.getId());
            return Result.success(studentInfo);
        }
        return Result.success(new Account());
    }

    @GetMapping("/logout")
    public Result logout(HttpServletRequest request){
        request.getSession().setAttribute("user",null);
        return Result.success();
    }
}
