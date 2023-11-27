package com.example.controller;

import com.example.common.Result;
import com.example.entity.AdminInfo;
import com.example.service.AdminInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/adminInfo")
public class AdminInfoController {

	//在该层调用Service层，则引入Service层
	@Resource
	private AdminInfoService adminInfoService;

	@PostMapping
	public Result add(@RequestBody AdminInfo adminInfo){
		adminInfoService.add(adminInfo);
		return Result.success();
	}

	@PutMapping
	public Result update(@RequestBody AdminInfo adminInfo){
		adminInfoService.update(adminInfo);
		return Result.success();
	}

	@GetMapping
	public Result findAll(){
		List<AdminInfo> list = adminInfoService.findAll();
		return Result.success(list);
	}

	@DeleteMapping("{id}")
	public Result deleteById(@PathVariable Long id){
		adminInfoService.deleteById(id);
		return Result.success();
	}

	@GetMapping("/page")
	public Result findPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize){//前台没传要给默认值
		PageInfo<AdminInfo> info = adminInfoService.findPage(pageNum,pageSize);
		return Result.success(info);
	}

	@GetMapping("/page/{name}")
	public Result findPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize,@PathVariable String name){//前台没传要给默认值
		PageInfo<AdminInfo> info = adminInfoService.findPageName(pageNum,pageSize,name);
		return Result.success(info);
	}

}
