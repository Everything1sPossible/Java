package com.wfu.web.domain;

/**
 *רҵ�γ��м�� 
 */
public class MajorCourse {

	private Integer mcId;   //�м����ˮ��
	private Integer mId;    //רҵ��ˮ��-->ÿ��רҵ�кܶ�γ�
	private String coIds;   //�γ���ˮ�ż�
	private String grade;   //�꼶
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
