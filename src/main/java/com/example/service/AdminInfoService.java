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

@Service
public class AdminInfoService {

    @Resource
    private AdminInfoDao adminInfoDao;


    public Account login(String name, String password) {

        // 通过用户名和密码去数据库里查一条记录出来
        AdminInfo adminInfo = adminInfoDao.findByNameAndPass(name, password);

        if (ObjectUtil.isEmpty(adminInfo)) {
            throw new CustomException("-1", "用户名、密码或角色错误");
        }

        return adminInfo;
    }

    public AdminInfo findById(Long id) {
        return adminInfoDao.selectByPrimaryKey(id);
    }

    public void update(AdminInfo adminInfo) {
        adminInfoDao.updateByPrimaryKeySelective(adminInfo);
    }

    public void add(AdminInfo adminInfo) {
        // 能否直接就插入数据库呢？
        // 1. 新增管理员，没有写密码字段，需要初始化一个密码

        // 1. 先查询数据库里面有没有同名的管理员
        AdminInfo info = adminInfoDao.findByName(adminInfo.getName());
        if (ObjectUtil.isNotEmpty(info)) {
            // 如果查到了，提示前台用户名已存在
            throw new CustomException(ResultCode.USER_EXIST_ERROR);
        }
        if (ObjectUtil.isEmpty(adminInfo.getPassword())) {
            // 没有密码的时候，给它初始化一个初始密码123456
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
        // 开启分页
        PageHelper.startPage(pageNum, pageSize);
        // 下面查询就会自动根据pageNum和pageSize来查对应的数据
        List<AdminInfo> infos = adminInfoDao.selectAll();
        return PageInfo.of(infos);
    }

    public PageInfo<AdminInfo> findPageName(Integer pageNum, Integer pageSize, String name) {
        // 开启分页
        PageHelper.startPage(pageNum, pageSize);
        List<AdminInfo> infos = adminInfoDao.findByNamePage(name);
        return PageInfo.of(infos);
    }
}
