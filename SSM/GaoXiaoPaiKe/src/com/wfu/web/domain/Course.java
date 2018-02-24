package com.wfu.web.domain;

/**
 * 课程表
 * @author Administrator
 *
 */
public class Course {
	
	private Integer coid;    //课程流水号
	private String coName;   //课程名称
	private String coNum;    //课程编号
	private String coState = "1";  //课程状态,默认为"1":正常使用,"2":弃用
	private String coLevel;  //课程类型:"1"公共必修课:,"2":学科基础与专业必修课,"3":专业限选课
	@Override
	public String toString() {
		return "Course [coid=" + coid + ", coName=" + coName + ", coNum="
				+ coNum + ", coState=" + coState + ", coLevel=" + coLevel + "]";
	}
	public Integer getCoid() {
		return coid;
	}
	public void setCoid(Integer coid) {
		this.coid = coid;
	}
	public String getCoName() {
		return coName;
	}
	public void setCoName(String coName) {
		this.coName = coName;
	}
	public String getCoNum() {
		return coNum;
	}
	public void setCoNum(String coNum) {
		this.coNum = coNum;
	}
	public String getCoState() {
		return coState;
	}
	public void setCoState(String coState) {
		this.coState = coState;
	}
	public String getCoLevel() {
		return coLevel;
	}
	public void setCoLevel(String coLevel) {
		this.coLevel = coLevel;
	}
}
