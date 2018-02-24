package com.wfu.web.domain;

/**
 *专业课程中间表 
 */
public class MajorCourse {

	private Integer mcId;   //中间表流水号
	private Integer mId;    //专业流水号-->每个专业有很多课程
	private String coIds;   //课程流水号集
	private String grade;   //年级
	@Override
	public String toString() {
		return "MajorCourse [mcId=" + mcId + ", mId=" + mId + ", coIds="
				+ coIds + ", grade=" + grade + "]";
	}
	public Integer getMcId() {
		return mcId;
	}
	public void setMcId(Integer mcId) {
		this.mcId = mcId;
	}
	public Integer getmId() {
		return mId;
	}
	public void setmId(Integer mId) {
		this.mId = mId;
	}
	public String getCoIds() {
		return coIds;
	}
	public void setCoIds(String coIds) {
		this.coIds = coIds;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
}
