// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) ansi
// Source File Name: PageManage.java
package com.truck.util;

import java.util.List;

/**
 * 类说明：分页方法封装
 * 
 * @author shiChao
 */
public class PageModule<T> {
	/**
	 * 总页数，通过总记录数和每页显示记录条数计算获得
	 */
	private int countPage;
	/**
	 * 总记录数
	 */
	private int total;
	/**
	 * 当前页，默认是第一页
	 */
	private int page = 1;
	/**
	 * 结果列表
	 */
	private List<T> rows = null;
	/**
	 * 每页显示记录条数 ，默认是每页显示10条记录
	 */
	private int pageSize = 10;
	/**
	 * 开始索引，通过当前页和每页显示记录条数计算获得
	 */
	private int startIndex;

	public PageModule() {
	}

	/**
	 * 两个参数的构造方法，调用该构造方法需要另行设置结果list
	 * 
	 * @param currentPage
	 *            当前页
	 * @param countRecord
	 *            总页数
	 */
	public PageModule(int page, int total) {
		this.page = page;
		this.total = total;
		calculate();
	}

	/**
	 * 能够设置一页显示多少条记录的构造方法
	 * 
	 * @param page
	 *            当前页
	 * @param total
	 *            总记录数
	 * @param pageSize
	 *            每页最多显示的记录条数
	 */
	public PageModule(int page, int total, int pageSize) {
		super();
		this.total = total;
		this.page = page;
		if (pageSize != 0) {
			this.pageSize = pageSize;
		}
		calculate();
	}

	/**
	 * 计算开始索引和总页数
	 */
	private void calculate() {
		// 计算开始索引
		this.startIndex = (page - 1) * pageSize;
		// 计算总页数
		this.countPage = (total % pageSize == 0) ? (total / pageSize)
				: (total / pageSize + 1);
	}
    
	public int getCountPage() {
		return countPage;
	}

	public int getTotal() {
		return total;
	}

	public int getPage() {
		return page;
	}

	public List<T> getRows() {
		return rows;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setCountPage(int countPage) {
		this.countPage = countPage;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
}
