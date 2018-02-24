package com.wfu.web.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wfu.services.XgmmServices;
import com.wfu.web.domain.SysLogin;

@Controller
public class XgmmController {
	
	/**
	 * 进入修改密码页面
	 */
	@RequestMapping("/xgmmView.html")
	public String xgmmView(){
		return "xxgl/xgmm";
	}
	
	@RequestMapping("/xgmm.html")
	public String xgmm(SysLogin sysLogin,HttpServletResponse response){
		xxglServices.updatePwd(sysLogin, response);
		return "xxgl/xgmm";
	}
	
	@Autowired
	private XgmmServices xxglServices = null;

}
