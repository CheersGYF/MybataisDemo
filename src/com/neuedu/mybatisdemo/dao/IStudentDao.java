package com.neuedu.mybatisdemo.dao;

import java.util.List;

import com.neuedu.mybatisdemo.bean.Student;
/**
 * 
 * @author gyf
 *
 */
public interface IStudentDao {
	/**
	 * 增加学生信息
	 * @param student 要新增的学生信息
	 * @return 返回数据库更新的行数
	 */
	public int addStudent(Student student);
	/**
	 * 
	 * @param student 修改的学生信息
	 * @return 返回数据库更新行数
	 */
	public int editStudent(Student student);
	/**
	 * 
	 * @param id 删除的学生id
	 * @return 返回数据库更新行数
	 */
	public int deleteStudent(int id);
	/**
	 * 
	 * @return
	 */
	public List<Student> findAll();
	/**
	 * 
	 * @return
	 */
	public List<Student> findAllInfo();
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Student findById(int id);
	
}
