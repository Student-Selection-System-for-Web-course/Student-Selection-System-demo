package com.example.controller;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.Result;
import com.example.entity.Account;
import com.example.entity.ClassInfo;
import com.example.entity.XuankeInfo;
import com.example.exception.CustomException;
import com.example.service.ClassInfoService;
import com.example.service.XuankeInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/classInfo")
public class ClassInfoController {
    @Resource
    private ClassInfoService classInfoService;
    @Resource
    private XuankeInfoService xuankeInfoService;

    @PostMapping
    public Result add(@RequestBody ClassInfo classInfo){
        classInfoService.add(classInfo);
        return Result.success();
    }

    @GetMapping
    public Result findAll(){
        List<ClassInfo> list = classInfoService.findAll();
        return Result.success(list);
    }

    @GetMapping("/{search}")
    public Result findSearch(@PathVariable String search){
        List<ClassInfo> list = classInfoService.findSearch(search);
        return Result.success(list);
    }

    @PutMapping
    public Result update(@RequestBody ClassInfo classInfo){
        classInfoService.update(classInfo);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        classInfoService.delete(id);
        return Result.success();
    }

    @PostMapping("/xuanke")
    public Result xuanke(@RequestBody ClassInfo classInfo, HttpServletRequest request){
        Account user = (Account) request.getSession().getAttribute("user");
        if(ObjectUtil.isEmpty(user)){
            throw new CustomException("-1","请重新登录");
        }
        //判断是否选过这门课
        XuankeInfo info = xuankeInfoService.find(classInfo.getName(),user.getId());
        if(ObjectUtil.isNotEmpty(info)){
            throw new CustomException("-1","已经选过该门课程！");
        }
        //把課程信息加入到選課信息表
        XuankeInfo xuankeInfo = new XuankeInfo();
        BeanUtils.copyProperties(classInfo,xuankeInfo);
        xuankeInfo.setId(null);
        //把選課信息表里剩下字段信信補全
        xuankeInfo.setStudentId(user.getId());
        xuankeInfo.setStatus("待开课");
        xuankeInfoService.add(xuankeInfo);
        //課程人數加一
        classInfo.setYixuan(classInfo.getYixuan() + 1);
        classInfoService.update(classInfo);
        return Result.success();
    }
}
