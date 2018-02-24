package com.wfu.web.domain;

import java.util.List;

/**
 * 分页结果类
 * @author Administrator
 *
 * @param <T>
 */
public class PageResult<T> {
	
	private List<T> dataList ; //存放查询数据 
	
	private long pageNo ;    //当前页
	
	private long pageSize ;  //数据条数
	
	private long total ;     //总条数
	
	private long pages ;     //总页数

	@Override
	public String toString() {
		return "PageResult [dataList=" + dataList + ", pageNo=" + pageNo
				+ ", pageSize=" + pageSize + ", total=" + total + ", pages="
				+ pages + "]";
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public long getPageNo() {
		return pageNo;
	}

	public void setPageNo(long pageNo) {
		this.pageNo = pageNo;
	}

	public long getPageSize() {
		return pageSize;
	}

	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getPages() {
		return pages;
	}

	public void setPages(long pages) {
		this.pages = pages;
	}

}
