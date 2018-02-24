package com.wfu.services;

import javax.servlet.http.HttpServletResponse;

import com.wfu.web.domain.SysLogin;

public interface XgmmServices {
	
	//ĞŞ¸ÄÃÜÂë
    public void updatePwd(SysLogin sysLogin,HttpServletResponse response);
	
}
