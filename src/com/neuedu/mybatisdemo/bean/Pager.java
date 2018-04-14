package com.neuedu.mybatisdemo.bean;

/**
 * 分页类
 *  搜索：mybatis 分页插件
 * @author gyf
 *
 */
public class Pager {
	// 默认查找第1页
	private int pageNum = 1;
	// 默认每页查3条
	private int pageSize = 3;
	// 查询条件
	private Cource cource;
	public Pager() {
		super();
	}
	
	public Pager(int pageNum, int pageSize) {
		super();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}

	public Pager(int pageNum, int pageSize, Cource cource) {
		super();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.cource = cource;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public Cource getCource() {
		return cource;
	}

	public void setCource(Cource cource) {
		this.cource = cource;
	}

	/**
	 * 返回n-1页最大数据条数
	 * @return (pageNum-1)*pageSize
	 */
	public int getBegin(){
	 return	(pageNum-1)*pageSize;
	}
	/**
	 * 返回n页最大数据条数
	 * @return pageNum*pageSize
	 */
	public int getEnd(){
		return pageNum*pageSize;
	}
	
}
