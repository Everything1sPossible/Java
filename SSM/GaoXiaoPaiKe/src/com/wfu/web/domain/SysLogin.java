package com.wfu.web.domain;

/**
 * ϵͳ�û���
 * @author sjh
 *
 */
public class SysLogin {
	
	private Integer   loginId;   //�û���ˮ��    
	private Integer     stuId;   //ѧ����ˮ��
	private Integer       tid;   //��ʦ��ˮ��
	private String  loginName;   //�û�����
	private String   username;   //�û���
	private String   password;   //����
	private String  loginUser;   //�û���ɫ :"1":"��������Ա","2":"��������쵼","3":"��ͨ��ʦ","4":"ѧ��"
	private String loginState = "1";   //�û�״̬,Ĭ��Ϊ1,��Ϊ����ʹ���˺�,'2'Ϊ�쳣�˺�
	private String  loginMenu;   //��Ӧ�˵�
	
	//��������,���������ݿ�
	private String kaptchaCode;      //��֤��
	private String menuId;         //�˵���ˮ��
	private String Newpassword;    //������
	
	@Override
	public String toString() {
		return "SysLogin [loginId=" + loginId + ", stuId=" + stuId + ", tid="
				+ tid + ", loginName=" + loginName + ", username=" + username
				+ ", password=" + password + ", loginUser=" + loginUser
				+ ", loginState=" + loginState + ", loginMenu=" + loginMenu + "]";
	}

	public Integer getLoginId() {
		return loginId;
	}
	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}
	public Integer getStuId() {
		return stuId;
	}
	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

	public String getLoginState() {
		return loginState;
	}

	public void setLoginState(String loginState) {
		this.loginState = loginState;
	}

	public String getLoginMenu() {
		return loginMenu;
	}

	public void setLoginMenu(String loginMenu) {
		this.loginMenu = loginMenu;
	}

	public String getKaptchaCode() {
		return kaptchaCode;
	}

	public void setKaptchaCode(String kaptchaCode) {
		this.kaptchaCode = kaptchaCode;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getNewpassword() {
		return Newpassword;
	}

	public void setNewpassword(String newpassword) {
		Newpassword = newpassword;
	}
}
