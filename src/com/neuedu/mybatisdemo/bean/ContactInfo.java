package com.neuedu.mybatisdemo.bean;

public class ContactInfo {
	private int studentId;
	private String qq;
	private String email;
	
	public ContactInfo() {
		//super();
	}
	public ContactInfo(int studentId, String qq, String email) {
		//super();
		this.studentId = studentId;
		this.qq = qq;
		this.email = email;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
