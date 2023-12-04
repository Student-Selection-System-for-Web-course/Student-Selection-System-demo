package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "student_info")

public class StudentInfo extends Account{
    @Column(name = "Sdept")
    private Long Sdept;

    @Column(name = "xingming")
    private String xingming;

    @Transient
    private String xueyuanName;

    public Long getSdept() {
        return Sdept;
    }

    public String getXingming() {
        return xingming;
    }

    public void setXingming(String xingming) {
        this.xingming = xingming;
    }

    public void setSdept(Long sdept) {
        Sdept = sdept;
    }

    public String getXueyuanName() {
        return xueyuanName;
    }

    public void setXueyuanName(String xueyuanName) {
        this.xueyuanName = xueyuanName;
    }
}
