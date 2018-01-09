package com.travel.bean.rq;

import org.apache.commons.lang3.StringUtils;

import com.travel.bean.constant.QueryConstant;

/**
 * 分页条件对象
 * @author user
 *
 */
public class PagerRq {
	/**
	 * 当前页码, 默认
	 */
	private Integer page =	Integer.valueOf(QueryConstant.DEFAULT_PAGENUMBER);
	/**
	 * 显示数据量, 默认
	 */
	private Integer size = Integer.valueOf(QueryConstant.DEFAULT_PAGESIZE);
	/**
	 * 排序字段，单个字段的排序，使用此对象中的方法
	 */
	private String sort;
	/**
	 * 排序呢方式
	 */
	private String order;
	/**
	 * 由于mysql中limit的参数不能使用计算公式，只能放置常量，因此需要这个字段
	 */
	private Integer start;
	
	public Integer getPage() {
		return page;
	}
	public PagerRq setPage(Integer page) {
		this.page = page;
		return this;
	}
	public Integer getSize() {
		return size;
	}
	public PagerRq setSize(Integer size) {
		this.size = size;
		return this;
	}
	public String getSort() {
		return sort;
	}
	public PagerRq setSort(String sort) {
		this.sort = sort;
		return this;
	}
	public String getOrder() {
		if(StringUtils.isNotEmpty(this.sort))
			return this.sort + " " + order;
		else
			return null;
	}
	public PagerRq setOrder(String order) {
		this.order = order;
		return this;
	}
	public Integer getStart() {
		if(start == null) return (page - 1) * size;
		return start;
	}
}
