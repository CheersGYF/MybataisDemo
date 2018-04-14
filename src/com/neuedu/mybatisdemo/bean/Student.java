package com.neuedu.mybatisdemo.bean;

import java.util.List;

/**
 * 
 * @author gyf
 *
 */
public class Student {
	private int id;
	private String account;
	private String password;
	private String truename;
	
	// 学生的课程与学生间是多对一的关系，反之学生可以与课程间是一对多的关系
	// 在student类中，设计一个集合属性用于保存该学生所有选择的课程表示
	// 一个学生对应（选择了）多门课程
	private List<Cource> cources;
	// 学生与其联系方式是一对一的关系
	private ContactInfo contactInfo;
	/**
	 * 空的构造方法
	 */
	public Student() {
		//super();
	}
	public Student(int id){
		this.id=id;
	}
	public Student(String account, String password, String truename) {
		
		this.account = account;
		this.password = password;
		this.truename = truename;
	}
	
	public Student(int id, String account, String password, String truename) {
		//super();
		this.id = id;
		this.account = account;
		this.password = password;
		this.truename = truename;
	}
	
	public Student(int id, String account, String password, String truename, List<Cource> cources) {
		//super();
		this.id = id;
		this.account = account;
		this.password = password;
		this.truename = truename;
		this.cources = cources;
	}
	
	public Student(int id, String account, String password, String truename, List<Cource> cources,
			ContactInfo contactInfo) {
		super();
		this.id = id;
		this.account = account;
		this.password = password;
		this.truename = truename;
		this.cources = cources;
		this.contactInfo = contactInfo;
	}
	
	public ContactInfo getContactInfo() {
		return contactInfo;
	}
	public void setContactInfo(ContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}
	public List<Cource> getCources() {
		return cources;
	}

	public void setCources(List<Cource> cources) {
		this.cources = cources;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTruename() {
		return truename;
	}
	public void setTruename(String truename) {
		this.truename = truename;
	}
	
	
}
