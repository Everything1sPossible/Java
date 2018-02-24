package com.wfu.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wfu.services.CdglServices;
import com.wfu.system.tools.jsonUtil;
import com.wfu.web.domain.PageResult;
import com.wfu.web.domain.SysMenu;

@Controller
public class CdglController {
	
	private static final Logger logger = LoggerFactory.getLogger(CdglController.class);
	
	/**
	 * ��һ�ν���˵��������
	 * @return
	 */
	@RequestMapping("/menuView.html")
	public String menuView(){
		return "xtgl/cdgl";
	}
	
	/**
	 * AJAX�˵���ѯ�����з�ҳ
	 * @param sysmenu
	 * @param model
	 * @RequestBody:����ǰ̨ajax json��ֵ
	 * @return
	 */
	@RequestMapping("/getAllMenu.html")
	public String getAllMenu(@RequestBody SysMenu sysmenu,HttpServletResponse response,Model model){
		PrintWriter pw = null ;
		try {
			pw = response.getWriter();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		PageResult<SysMenu> result = cdglServices.getAllMenu(sysmenu);
		String json = jsonUtil.pageToJson(result, null, null);
		pw.print(json);
		pw.flush();
		pw.close();
		return "xtgl/cdgl";
	}
	
	/**
	 * ����༭/��Ӳ˵�
	 * @param sysmenu
	 * @return
	 */
	@RequestMapping("/updateMenuView.html")
	public String updateMenuView(SysMenu sysmenu,Model model){
		String menuIdHead = Integer.toString(sysmenu.getMenuId()).substring(0, 1);
		//һ���˵�
		if(menuIdHead.equals("1")){
			Map<String, Object> menuMap1 = cdglServices.queryById1(sysmenu.getMenuId());
			model.addAttribute("menuMap",menuMap1);
		}
		//�����˵�
		if(menuIdHead.equals("2")){
			Map<String, Object> menuMap2 = cdglServices.queryById2(sysmenu.getMenuId());
			model.addAttribute("menuMap",menuMap2);
		}
		return "/xtgl/cdgl_update";
	}
	
	/**
	 * ����һ���˵������б�
	 * @return
	 */
	@RequestMapping("/getFMenu.html")
	public String getFMenu(HttpServletResponse response){
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		List<Map<String, Object>> FMenuList = cdglServices.getFMenu();
		String json = jsonUtil.objectToJson(FMenuList, null, null);
		pw.print(json);
		pw.flush();
		pw.close();
		return "/xtgl/cdgl_update";
	}
	/**
	 * ��Ӳ˵�
	 * @param sysmenu
	 * @param response
	 * @return
	 */
	@RequestMapping("/addMenu.html")
	public String addMenu(@RequestBody SysMenu sysmenu,HttpServletResponse response){
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		String json = cdglServices.addMenu(sysmenu);
		pw.print(json);
		pw.flush();
		pw.close();
		return "/xtgl/cdgl_update";
	}
	
	/**
	 * �޸Ĳ˵�
	 * @param sysmenu
	 * @return
	 */
	@RequestMapping("/updateMenu.html")
	public String updateMenu(@RequestBody SysMenu sysmenu,HttpServletResponse response){
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		String json = cdglServices.uodateMenu(sysmenu);
		pw.print(json);
		pw.flush();
		pw.close();
		return "/xtgl/cdgl_update";
	}
	
	@Autowired
	private CdglServices cdglServices = null;

}
