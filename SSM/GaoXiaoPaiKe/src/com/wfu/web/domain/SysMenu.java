package com.wfu.web.domain;

/**
 * ϵͳ�˵���
 * @author sjh
 *
 */
public class SysMenu {
	
	private    int     menuId;  //�˵���ˮ��
	private String   menuName;  //�˵�����
	private String    menuUrl;  //�˵�url
	private String  menuLevel;  //�˵�����
	private    int menuFather;  //��һ���˵���ˮ��
	private String  menuState = "1";  //�˵�״̬,Ĭ��Ϊ"1",����ʹ����״̬
	
	//�����ֶ�
	private String qmenuName;   //��ѯ�Ĳ˵�����
	private int pageNum = 1 ;       //�ڼ�ҳ,Ĭ�ϵ�һҳ
	private int pageSize = 5 ;      //ÿҳ����������,Ĭ��ÿҳ5������
	
	@Override
	public String toString() {
		return "sysMenu [menuId=" + menuId + ", menuName=" + menuName
				+ ", menuUrl=" + menuUrl + ", menuLevel=" + menuLevel
				+ ", menuFather=" + menuFather + ", menuState=" + menuState
				+ "]";
	}
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public String getMenuLevel() {
		return menuLevel;
	}
	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
	}
	public int getMenuFather() {
		return menuFather;
	}
	public void setMenuFather(int menuFather) {
		this.menuFather = menuFather;
	}
	public String getMenuState() {
		return menuState;
	}
	public void setMenuState(String menuState) {
		this.menuState = menuState;
	}
	public String getQmenuName() {
		return qmenuName;
	}
	public void setQmenuName(String qmenuName) {
		this.qmenuName = qmenuName;
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
}
