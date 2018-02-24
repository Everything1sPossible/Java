package com.wfu.web.domain;

import java.util.List;

/**
 * ��ҳ�����
 * @author Administrator
 *
 * @param <T>
 */
public class PageResult<T> {
	
	private List<T> dataList ; //��Ų�ѯ���� 
	
	private long pageNo ;    //��ǰҳ
	
	private long pageSize ;  //��������
	
	private long total ;     //������
	
	private long pages ;     //��ҳ��

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
