package com.wfu.web.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wfu.services.LoginServices;
import com.wfu.system.tools.Base64Util;
import com.wfu.web.domain.SysLogin;

@Controller
public class loginController {
	
	/**
	 *登录页面 
	 */
	@RequestMapping("/login.html")
	public String login(){
		return "main/login";
	}
	/**
	 * 登录校验
	 */
	@RequestMapping("/checkLogin.html")
	public String checkLogin(SysLogin sysLogin,HttpServletRequest request,HttpServletResponse response) throws Exception{
		PrintWriter pw = response.getWriter();
		JSONObject jsonResult = loginServices.checkLogin(sysLogin,request);
		pw.print(jsonResult.toString());
		pw.flush();
		pw.close();
		return "main/login";
	}
	/**
	 *登录成功之后进入的页面 
	 */
	@RequestMapping("/main.html")
	public String main(SysLogin sysLogin,HttpServletRequest request){
		String menuIds = sysLogin.getMenuId();
	    loginServices.getMenu(Base64Util.base64ToStr(menuIds), request);
		return "main/top";
	}
	/**
	 * 返回按钮统一返回到主页面
	 */
	@RequestMapping("/main2.html")
	public String main2(){
		return "main/top";
	}
	/**
	 *系统退出 
	 */
	@RequestMapping("/loginOut.html")
	public String loginOut(){
		return "main/loginOut";
	}
	
	@Autowired
	private LoginServices loginServices = null ;

}
