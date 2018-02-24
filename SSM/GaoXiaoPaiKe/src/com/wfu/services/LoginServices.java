package com.wfu.services;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.wfu.web.domain.SysLogin;

public interface LoginServices {
	
	public JSONObject checkLogin(SysLogin sysLogin,HttpServletRequest request);
	
	public void getMenu(String FMenuId,HttpServletRequest request);

}
