package com.wfu.web.domain;

/**
 * רҵ��
 */
public class Major {
	
	private Integer mId;       //רҵ��ˮ��
	private String mName;      //רҵ����
	private String department; //����Ժϵ
	@Override
	public String toString() {
		return "Major [mId=" + mId + ", mName=" + mName + ", department="
				+ department + "]";
	}
	public Integer getmId() {
		return mId;
	}
	public void setmId(Integer mId) {
		this.mId = mId;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
 
}
