package com.wfu.web.domain;


/**
 * ��ʦ��
 * @author sjh
 *
 */
public class Teacher {
	
	private    int tid;          //��ʦ��ˮ��
	private String tname;        //��ʦ����
	private String tnumber;      //��ʦ���
	private String course;       //��ʦ�ν̿γ�
	private String department;   //��ʦ����Ժϵ
	private String role;         //������ɫ::"2":�쵼,"3":��ͨ��ʦ
	
	//�����ֶ�
	private String qtname;       //��ѯ�Ľ�ʦ����
	private String qtnumber;     //��ѯ�Ľ�ʦ���
	private int pageNum = 1;      //��ҳ��ǰҳ,Ĭ��Ϊ��1ҳ
	private int pageSize = 5;     //��ҳÿҳ������,Ĭ��ÿҳ5��
	private String tids = null;         //��ʦid����
	
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
