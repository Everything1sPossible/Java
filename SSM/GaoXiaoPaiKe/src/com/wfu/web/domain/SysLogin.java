package com.wfu.web.domain;

/**
 * 系统用户表
 * @author sjh
 *
 */
public class SysLogin {
	
	private Integer   loginId;   //用户流水号    
	private Integer     stuId;   //学生流水号
	private Integer       tid;   //教师流水号
	private String  loginName;   //用户名称
	private String   username;   //用户名
	private String   password;   //密码
	private String  loginUser;   //用户角色 :"1":"超级管理员","2":"教务管理领导","3":"普通教师","4":"学生"
	private String loginState = "1";   //用户状态,默认为1,即为正常使用账号,'2'为异常账号
	private String  loginMenu;   //对应菜单
	
	//其他属性,不存入数据库
	private String kaptchaCode;      //验证码
	private String menuId;         //菜单流水号
	private String Newpassword;    //新密码
	
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
