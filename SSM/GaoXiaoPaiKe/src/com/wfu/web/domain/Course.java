package com.wfu.web.domain;

/**
 * �γ̱�
 * @author Administrator
 *
 */
public class Course {
	
	private Integer coid;    //�γ���ˮ��
	private String coName;   //�γ�����
	private String coNum;    //�γ̱��
	private String coState = "1";  //�γ�״̬,Ĭ��Ϊ"1":����ʹ��,"2":����
	private String coLevel;  //�γ�����:"1"�������޿�:,"2":ѧ�ƻ�����רҵ���޿�,"3":רҵ��ѡ��
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
