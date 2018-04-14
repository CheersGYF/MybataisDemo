package com.neuedu.mybatisdemo.bean;

import java.util.Date;
import java.util.List;

/***
 * 
 * @author hp
 *
 */
public class Cource {
	// 编号
	private  String courceId;
	//名称
	private String courceName;
	// 学分 
	private Integer courceScore;
	// 开课时间
	private Date startDate;
	
	private List<Student> students;
	
	public Cource() {
		
	}
	
	public Cource(String courceId, String courceName, Integer courceScore, Date startDate) {
		//super();
		this.courceId = courceId;
		this.courceName = courceName;
		this.courceScore = courceScore;
		this.startDate = startDate;
	}
	
	public Cource(String courceId, String courceName, Integer courceScore, Date startDate, List<Student> students) {
		//super();
		this.courceId = courceId;
		this.courceName = courceName;
		this.courceScore = courceScore;
		this.startDate = startDate;
		this.students = students;
	}
	
	
	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public String getCourceId() {
		return courceId;
	}
	public void setCourceId(String courceId) {
		this.courceId = courceId;
	}
	public String getCourceName() {
		return courceName;
	}
	public void setCourceName(String courceName) {
		this.courceName = courceName;
	}
	public Integer getCourceScore() {
		return courceScore;
	}
	public void setCourceScore(Integer courceScore) {
		this.courceScore = courceScore;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	
	
}
