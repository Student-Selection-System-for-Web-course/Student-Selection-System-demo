package com.example.controller;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.Result;
import com.example.entity.Account;
import com.example.entity.AdminInfo;
import com.example.entity.StudentInfo;
import com.example.entity.TeacherInfo;
import com.example.service.AdminInfoService;
import com.example.service.StudentInfoService;
import com.example.service.TeacherInfoService;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 描述：跟账号相关的接口
 */
@RestController
@RequestMapping
public class AccountController {

    @Resource
    private AdminInfoService adminInfoService;
    @Resource
    private TeacherInfoService teacherInfoService;
    @Resource
    private StudentInfoService studentInfoService;


    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // png类型
//        SpecCaptcha captcha = new SpecCaptcha(135, 33, 6);
//        captcha.setCharType(Captcha.TYPE_NUM_AND_UPPER);
//        CaptchaUtil.out(captcha, request, response);

        // 算术类型
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(135, 33);
        captcha.setLen(4);  // 几位数运算，默认是两位
        captcha.getArithmeticString();  // 获取运算的公式：3+2=?
        captcha.text();  // 获取运算的结果：5
        CaptchaUtil.out(captcha, request, response);
    }

    /**
     * 描述：登录功能
     * @param user 登录用户信息
     * @param request request请求
     * @return Result
     */
    @PostMapping("/login")
    public Result login(@RequestBody Account user, HttpServletRequest request) {

        // 校验数据有没有填
        if (ObjectUtil.isEmpty(user.getName()) || ObjectUtil.isEmpty(user.getPassword()) || ObjectUtil.isEmpty(user.getLevel())) {
            return Result.error("-1", "请完善输入信息");
        }

        if (!CaptchaUtil.ver(user.getVerCode(), request)) {
            // 清除session中的验证码
            CaptchaUtil.clear(request);
            return Result.error("1001", "验证码不正确");
        }

        Integer level = user.getLevel();
        Account loginUser = new Account();
        if (1 == level) {
            // 管理员登录
            loginUser = adminInfoService.login(user.getName(), user.getPassword());
        }
        if (2 == level) {
            // 教师登录
            loginUser = teacherInfoService.login(user.getName(), user.getPassword());
        }
        if (3 == level) {
            // 学生登录
            loginUser = studentInfoService.login(user.getName(), user.getPassword());
        }

        // 在session里面把用户信息存一份
        request.getSession().setAttribute("user", loginUser);

        return Result.success(loginUser);
    }

    @PostMapping("/register")
    public Result register(@RequestBody Account user, HttpServletRequest request) {
        // 校验数据有没有填
        if (ObjectUtil.isEmpty(user.getName()) || ObjectUtil.isEmpty(user.getPassword()) || ObjectUtil.isEmpty(user.getLevel())) {
            return Result.error("-1", "请完善输入信息");
        }

        Integer level = user.getLevel();
        if (2 == level) {
            // 教师注册
            TeacherInfo teacherInfo = new TeacherInfo();
            BeanUtils.copyProperties(user, teacherInfo);
            teacherInfoService.add(teacherInfo);
        }
        if (3 == level) {
            // 学生注册
            StudentInfo studentInfo = new StudentInfo();
            BeanUtils.copyProperties(user, studentInfo);
            studentInfoService.add(studentInfo);
        }

        return Result.success();
    }

    /**
     * 获取当前登录的用户
     * @param request
     * @return
     */
    @GetMapping("/getUser")
    public Result getUser(HttpServletRequest request) {
        // 先从session里面获取当前存的登录用户信息
        Account user = (Account) request.getSession().getAttribute("user");
        // 判断当前登录的用户是什么角色
        Integer level = user.getLevel();
        if (1 == level) {
            // 获取管理员
            AdminInfo adminInfo = adminInfoService.findById(user.getId());
            return Result.success(adminInfo);
        }
        if (2 == level) {
            // 从教师表里面获取教师信息
            TeacherInfo teacherInfo = teacherInfoService.findById(user.getId());
            return Result.success(teacherInfo);
        }
        if (3 == level) {
            // 从学生表里面获取学生信息
            StudentInfo studentInfo = studentInfoService.findById(user.getId());
            return Result.success(studentInfo);
        }
        return Result.success(new Account());
    }

    @PostMapping("/updatePassword")
    public Result updatePassword(@RequestBody Account account, HttpServletRequest request) {
        // 1. 知道当前登录用户是哪个角色
        Account user = (Account) request.getSession().getAttribute("user");
        // 2. 判断原密码对不对
        String oldPassword = account.getPassword();
        if (!user.getPassword().equals(oldPassword)) {
            return Result.error("-1", "原密码输入错误");
        }
        String newPassword = account.getNewPassword();
        // 判断当前登录的用户是什么角色，根据角色，去对应的数据库表里更新密码
        Integer level = user.getLevel();
        if (1 == level) {
            AdminInfo adminInfo = new AdminInfo();
            BeanUtils.copyProperties(user, adminInfo);
            adminInfo.setPassword(newPassword);
            adminInfoService.update(adminInfo);
        }
        if (2 == level) {
            TeacherInfo teacherInfo = new TeacherInfo();
            BeanUtils.copyProperties(user, teacherInfo);
            teacherInfo.setPassword(newPassword);
            teacherInfoService.update(teacherInfo);
        }
        if (3 == level) {
            StudentInfo studentInfo = new StudentInfo();
            BeanUtils.copyProperties(user, studentInfo);
            studentInfo.setPassword(newPassword);
            studentInfoService.update(studentInfo);
        }
        // 3. 把session中的登录用户信息给清掉
        request.getSession().setAttribute("user", null);
        return Result.success();
    }

    @GetMapping("/logout")
    public Result logout(HttpServletRequest request) {
        request.getSession().setAttribute("user", null);
        return Result.success();
    }
}
