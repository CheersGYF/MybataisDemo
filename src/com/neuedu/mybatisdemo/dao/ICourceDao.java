package com.neuedu.mybatisdemo.dao;

import java.util.List;

import com.neuedu.mybatisdemo.bean.Cource;
import com.neuedu.mybatisdemo.bean.Pager;
/**
 * 
 * @author gyf
 *
 */
public interface ICourceDao {
	/**
	 *  查询课程名称
	 * @return 课程列表
	 */
	public List<Cource> findAll();
	/**
	 * 根据课程id查找课程信息
	 * @param id
	 * @return 课程信息
	 */
	public Cource findById(String id);
	/**
	 * 增加课程
	 * @param cource 课程信息
	 */
	public void addCource(Cource cource);
	/**
	 * 修改课程信息
	 * @param cource 修改后的课程信息
	 */
	public void updateCource(Cource cource);
	/**
	 * 批量更新用户信息
	 * @param list 更新用户参数列表
	 */
	public void beatuchUpdate(List<String> list);
	/**
	 * 通过课程id删除课程信息
	 * @param id 课程id
	 */
	public void deleteCource(String id);
	
	/**
	 * 用于查询时间在某个点之后的数据信息
	 * @param cource 课程对象
	 * @return 查询到的
	 */
	public List<Cource> searchByDate(Cource cource);
	/**
	 * 根据给定的时间参数，进行补丁条件查询，时间取大于记性计算
	 * 字符信息采用模糊查询
	 * @param cource 课程信息
	 * @return 查询的课程列表
	 */
	public List<Cource> searchByExample(Cource cource);
	
	/**
	 * 根据课程名称进行模糊查询，并对查询结果进行分页
	 * @param pager 页码对象作为参数
	 * @return 返回查找结果列表
	 */
	public List<Cource> searchByPage(Pager pager);
}
