package com.wfu.web.domain;


/**
 * 学生表
 * @author sjh
 *
 */
public class Student {
	
	private    int stuId;       //学生流水号
	private String stuName;     //学生姓名
	private String stuNumbet;   //学生编号
	private String department;  //学生所属院系
	private String major;       //学生所属专业
	
	@Override
	public String toString() {
		return "student [stuId=" + stuId + ", stuName=" + stuName
				+ ", stuNumbet=" + stuNumbet + ", department=" + department
				+ ", major=" + major + "]";
	}
	public int getStuId() {
		return stuId;
	}
	public void setStuId(int stuId) {
		this.stuId = stuId;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getStuNumbet() {
		return stuNumbet;
	}
	public void setStuNumbet(String stuNumbet) {
		this.stuNumbet = stuNumbet;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	
}
