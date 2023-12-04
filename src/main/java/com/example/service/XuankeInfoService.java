package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.dao.ClassInfoDao;
import com.example.dao.StudentInfoDao;
import com.example.dao.XuankeInfoDao;
import com.example.entity.Account;
import com.example.entity.ClassInfo;
import com.example.entity.StudentInfo;
import com.example.entity.XuankeInfo;
import com.example.exception.CustomException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class XuankeInfoService {
    @Resource
    private XuankeInfoDao xuankeInfoDao;
    @Resource
    private StudentInfoDao studentInfoDao;
    @Resource
    private ClassInfoDao classInfoDao;

    public List<XuankeInfo> findAll(HttpServletRequest request) {
        Account user = (Account) request.getSession().getAttribute("user");
        List<XuankeInfo> list;
        if (ObjectUtil.isEmpty(user)) {
            throw new CustomException("-1", "当前登录已失效");
        }
        if (1 == user.getLevel()) {
            //管理员
            list = xuankeInfoDao.selectAll();
        } else {
            //学生
            list = xuankeInfoDao.findByCondition(user.getId());
        }

        for (XuankeInfo xuankeInfo : list) {
            StudentInfo studentInfo = studentInfoDao.selectByPrimaryKey(xuankeInfo.getStudentId());
            xuankeInfo.setStudentName(studentInfo.getXingming());
        }
        return list;
    }

    public void add(XuankeInfo xuankeInfo) {
        xuankeInfoDao.insertSelective(xuankeInfo);
    }

    public XuankeInfo find(String name, Long id) {
        return xuankeInfoDao.find(name, id);
    }

    public void delete(Long id) {
        XuankeInfo xuankeInfo = xuankeInfoDao.selectByPrimaryKey(id);
        ClassInfo classInfo = classInfoDao.findByName(xuankeInfo.getName());
        xuankeInfoDao.deleteByPrimaryKey(id);
        classInfo.setYixuan(classInfo.getYixuan() - 1);
        classInfoDao.updateByPrimaryKeySelective(classInfo);
    }
}
