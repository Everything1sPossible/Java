package com.wfu.web.domain;

/**
 * 专业表
 */
public class Major {
	
	private Integer mId;       //专业流水号
	private String mName;      //专业名称
	private String department; //所属院系
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
