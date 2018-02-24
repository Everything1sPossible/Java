package com.wfu.web.domain;


/**
 * 教师表
 * @author sjh
 *
 */
public class Teacher {
	
	private    int tid;          //教师流水号
	private String tname;        //教师姓名
	private String tnumber;      //教师编号
	private String course;       //教师任教课程
	private String department;   //教师所属院系
	private String role;         //所属角色::"2":领导,"3":普通教师
	
	//辅助字段
	private String qtname;       //查询的教师姓名
	private String qtnumber;     //查询的教师编号
	private int pageNum = 1;      //分页当前页,默认为第1页
	private int pageSize = 5;     //分页每页数据数,默认每页5条
	private String tids = null;         //教师id集合
	
	@Override
	public String toString() {
		return "Teacher [tid=" + tid + ", tname=" + tname + ", tnumber="
				+ tnumber + ", course=" + course + ", department=" + department
				+ ", role=" + role + "]";
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getTnumber() {
		return tnumber;
	}
	public void setTnumber(String tnumber) {
		this.tnumber = tnumber;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getQtname() {
		return qtname;
	}
	public void setQtname(String qtname) {
		this.qtname = qtname;
	}
	public String getQtnumber() {
		return qtnumber;
	}
	public void setQtnumber(String qtnumber) {
		this.qtnumber = qtnumber;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getTids() {
		return tids;
	}
	public void setTids(String tids) {
		this.tids = tids;
	}
}
