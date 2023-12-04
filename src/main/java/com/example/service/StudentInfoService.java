package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.ResultCode;
import com.example.dao.StudentInfoDao;
import com.example.dao.XueyuanInfoDao;
import com.example.entity.Account;
import com.example.entity.StudentInfo;
import com.example.entity.XueyuanInfo;
import com.example.exception.CustomException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentInfoService {
    @Resource
    private StudentInfoDao studentInfoDao;
    @Resource
    private XueyuanInfoDao xueyuanInfoDao;

    public Account login(String name, String password) {
        //数据库中根据学号和密码查询信息
        StudentInfo studentInfo = studentInfoDao.findByNameAndPassword(name,password);
        if(ObjectUtil.isEmpty(studentInfo)){
            throw new CustomException("-1","学号、密码或角色错误");
        }
        return studentInfo;
    }

    public void register(StudentInfo studentInfo) {
        //从数据库中查询信息，看是否重复
        StudentInfo info = studentInfoDao.findByName(studentInfo.getName());
        if(ObjectUtil.isNotEmpty(info)){
            throw new CustomException(ResultCode.USER_EXIST_ERROR);
        }
        studentInfoDao.insertSelective(studentInfo);
    }

    public StudentInfo findById(Long id) {
        return studentInfoDao.selectByPrimaryKey(id);
    }

    public void update(StudentInfo studentInfo) {
        studentInfoDao.updateByPrimaryKeySelective(studentInfo);
    }

    public List<StudentInfo> findAll() {
        List<StudentInfo> list = studentInfoDao.selectAll();
        //处理list，把学生信息对应学院名称弄进去
        //方式1:
        //for(StudentInfo studentInfo : list){
            //根据学院id查询学院信息，再拿到学院名称name.赋值给
        //    if(ObjectUtil.isNotEmpty(studentInfo.getSdept())){
        //        XueyuanInfo xueyuanInfo = xueyuanInfoDao.selectByPrimaryKey(studentInfo.getSdept());
        //        studentInfo.setXueyuanName(xueyuanInfo.getName());
        //    }
        //}
        //return list;
        //方式2:使用sql关联查询语句
        List<StudentInfo> list2 = studentInfoDao.findAllJoinXueyuan();
        return list2;

    }

    public void add(StudentInfo studentInfo) {
        StudentInfo info = studentInfoDao.findByName(studentInfo.getName());
        //看学号是否有重复
        if(ObjectUtil.isNotEmpty(info)){
            throw new CustomException(ResultCode.USER_EXIST_ERROR);
        }
        //若没有重复，初始化密码
        if(ObjectUtil.isEmpty(studentInfo.getPassword())){
            studentInfo.setPassword("123456");
        }
        studentInfoDao.insertSelective(studentInfo);
    }

    public void deleteById(Long id) {
        studentInfoDao.deleteByPrimaryKey(id);
    }
}
