package com.example.controller;

import com.example.common.Result;
import com.example.entity.StudentInfo;
import com.example.entity.XuankeInfo;
import com.example.service.XuankeInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("/xuankeInfo")
public class XuankeInfoController {

    @Resource
    private XuankeInfoService xuankeInfoService;

    @GetMapping
    public Result findAll(HttpServletRequest request) {
        List<XuankeInfo> list = xuankeInfoService.findAll(request);
        return Result.success(list);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        xuankeInfoService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody XuankeInfo xuankeInfo) {
        xuankeInfoService.update(xuankeInfo);
        return Result.success();
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, HttpServletRequest request) {
        PageInfo<XuankeInfo> info = xuankeInfoService.findPage(pageNum, pageSize, request);
        return Result.success(info);
    }

    @GetMapping("/page/{name}")
    public Result findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @PathVariable String name, HttpServletRequest request) {
        PageInfo<XuankeInfo> info = xuankeInfoService.findPageName(pageNum, pageSize, name, request);
        return Result.success(info);
    }
}
