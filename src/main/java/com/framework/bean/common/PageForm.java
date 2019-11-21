/*
 * Copyright (c) 2013 LIAONING SHIDAI_WANHENG CO.,LTD. All Rights Reserved.
 * This work contains SHIDAI_WANHENG CO.,LTD.'s unpublished
 * proprietary information which may constitute a trade secret
 * and/or be confidential. This work may be used only for the
 * purposes for which it was provided, and may not be copied
 * or disclosed to others. Copyright notice is precautionary
 * only, and does not imply publication.
 *
 */
package com.framework.bean.common;

/**
 * 分页表单基类
 *
 * @author Neo.Yin
 * @version 1.0	2019-7-25	Neo.Yin		created.
 * @version <ver>
 */
public class PageForm implements java.io.Serializable {

	private static final long	serialVersionUID	= 1L;

	private int					page;
	private int					rows;
	private String				sort;
	private String				order;
	private String				group;

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		if (sort == null || sort.trim().length() == 0) {
			this.sort = null;
		} else {
			this.sort = sort.trim();
		}
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		if (order == null || order.trim().length() == 0) {
			this.order = null;
		} else {
			this.order = order.trim();
		}
	}

	public int getStartIndex() {
		int startIndex = (this.page - 1) * this.rows;
		if (startIndex < 0) startIndex = 0;
		return startIndex;
	}
}
