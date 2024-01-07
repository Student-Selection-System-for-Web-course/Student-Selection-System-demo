package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.ResultCode;
import com.example.dao.TeacherInfoDao;
import com.example.dao.ZhuanyeInfoDao;
import com.example.entity.Account;
import com.example.entity.TeacherInfo;
import com.example.entity.ZhuanyeInfo;
import com.example.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class TeacherInfoService {

    @Resource
    private TeacherInfoDao teacherInfoDao;
    @Resource
    private ZhuanyeInfoDao zhuanyeInfoDao;

    public Account login(String name, String password) {
        // 通过用户名和密码去数据库里查一条记录出来
        TeacherInfo teacherInfo = teacherInfoDao.findByNameAndPass(name, password);

        if (ObjectUtil.isEmpty(teacherInfo)) {
            throw new CustomException("-1", "用户名、密码或角色错误");
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
        // 1. 检测数据库中有没有同名的教师，如果有，需要提示前台用户重新输入
        TeacherInfo info = teacherInfoDao.findByName(teacherInfo.getName());
        if (ObjectUtil.isNotEmpty(info)) {
            throw new CustomException(ResultCode.USER_EXIST_ERROR);
        }
        if (ObjectUtil.isEmpty(teacherInfo.getPassword())) {
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
        for (TeacherInfo teacherInfo : list) {
            ZhuanyeInfo zhuanyeInfo = zhuanyeInfoDao.selectByPrimaryKey(teacherInfo.getZhuanyeId());
            if (ObjectUtil.isNotEmpty(zhuanyeInfo)) {
                teacherInfo.setZhuanyeName(zhuanyeInfo.getName());
            }
        }
        return PageInfo.of(list);
    }

    public PageInfo<TeacherInfo> findPageSearch(String search, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<TeacherInfo> teacherInfos = teacherInfoDao.findByLikeName(search);
        for (TeacherInfo teacherInfo : teacherInfos) {
            ZhuanyeInfo zhuanyeInfo = zhuanyeInfoDao.selectByPrimaryKey(teacherInfo.getZhuanyeId());
            if (ObjectUtil.isNotEmpty(zhuanyeInfo)) {
                teacherInfo.setZhuanyeName(zhuanyeInfo.getName());
            }
        }
        return PageInfo.of(teacherInfos);
    }
}
