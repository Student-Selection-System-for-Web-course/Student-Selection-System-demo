package com.example.entity;

import javax.persistence.*;

@Table(name = "class_info")
public class ClassInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;


    @Column(name = "score")
    private Integer score;

    @Column(name = "kaiban")
    private Integer kaiban;

    @Column(name = "time")
    private String time;

    @Column(name = "yixuan")
    private Integer yixuan;

    @Column(name = "zhuanyeId")
    private Long zhuanyeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getKaiban() {
        return kaiban;
    }

    public void setKaiban(Integer kaiban) {
        this.kaiban = kaiban;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getYixuan() {
        return yixuan;
    }

    public void setYixuan(Integer yixuan) {
        this.yixuan = yixuan;
    }

    public Long getZhuanyeId() {
        return zhuanyeId;
    }

    public void setZhuanyeId(Long zhuanyeId) {
        this.zhuanyeId = zhuanyeId;
    }
}
