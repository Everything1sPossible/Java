package com.wfu.web.domain;


/**
 * ѧ����
 * @author sjh
 *
 */
public class Student {
	
	private    int stuId;       //ѧ����ˮ��
	private String stuName;     //ѧ������
	private String stuNumbet;   //ѧ�����
	private String department;  //ѧ������Ժϵ
	private String major;       //ѧ������רҵ
	
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
