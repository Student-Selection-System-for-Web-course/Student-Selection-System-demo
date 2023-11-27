package com.example.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Account {
	//注解为Id
	@Id
	//@GeneratedValue注解存在的意义主要就是为一个实体生成一个唯一标识的主键
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//@Column注解用来标识实体类中属性与数据表中字段的对应关系。name属性定义了被标注字段在数据库表中所对应字段的名称；
	@Column(name="name")
	private String name;

	@Column(name="password")
	private String password;

	@Column(name="sex")
	private String sex;

	@Column(name="age")
	private String age;

	@Column(name="level")
	private int level;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}
