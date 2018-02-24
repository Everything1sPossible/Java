package com.wfu.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
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
import com.wfu.services.TeacherglServices;
import com.wfu.system.tools.jsonUtil;
import com.wfu.web.domain.PageResult;
import com.wfu.web.domain.SysLogin;
import com.wfu.web.domain.Teacher;

@Controller
public class TeacherglController {
	
	private static final Logger logger = LoggerFactory.getLogger(TeacherglController.class);

	/**
	 * ��һ�ν����ʦ����ҳ��
	 * @return
	 */
	@RequestMapping("/teacherView.html")
	public String teacherView(){
		return "xtgl/teachergl";
	}
	/**
	 * ��ʼ���γ������б�
	 */
	@RequestMapping("/getCourseList.html")
	public String getCourseList(HttpServletResponse response){
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		List<Map<String,Object>> list = teacherglServices.getCourseList();
		String json = jsonUtil.objectToJson(list, null, null);
		pw.print(json);
		pw.flush();
		pw.close();
		return "xtgl/teacherAdd";
	}
	/**
	 * ������ѯ��ȡȫ����ʦ
	 */
	@RequestMapping("/queryByPage.html")
    public String queryByPage(@RequestBody Teacher teacher,HttpServletResponse response){
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		PageResult<Teacher> result = teacherglServices.queryByPage(teacher);
		String json = jsonUtil.pageToJson(result, null, null);
		pw.print(json);
		pw.flush();
		pw.close();
    	return "xtgl/teachergl";
    }	
	
	/**
	 * ������ӽ�ʦҳ��
	 */
	@RequestMapping("/addTeacherView.html")
	public String addTeacherView(){
		return "xtgl/teacherAdd";
	}
	/**
	 * ��ӽ�ʦ
	 * @return
	 */
	@RequestMapping("/addTeacher.html")
	public String addTeacher(@RequestBody Teacher teacher,HttpServletResponse response){
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		String json = teacherglServices.addTeacher(teacher);
		pw.print(json);
		pw.flush();
		pw.close();
		return "xtgl/teacherAdd";
	}
	/**
	 * ����ע���˺�ҳ��
	 */
	@RequestMapping("/singupView.html")
	public String singupView(Teacher teacher,Model model){
		String tname = null;
		try {
			//���get��������
			tname = new String(teacher.getTname().getBytes("iso8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		}
		Map<String,Object> tmap = new HashMap<String,Object>();
		tmap.put("tid", teacher.getTid());
		tmap.put("tname", tname);
		tmap.put("tnumber", teacher.getTnumber());
		tmap.put("role", teacher.getRole());
		model.addAttribute("tmap", tmap);
		return "xtgl/singupForT";
	}
	/**
	 * ��ʼ����Ӧ�˵������б�
	 */
	@RequestMapping("/loginMenuList.html")
	public String loginMenuList(HttpServletResponse response){
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
		return "xtgl/singupForT";
	}
	/**
	 * ע���˺�
	 */
	@RequestMapping("/singupNewLogin.html")
	public String singupNewLogin(@RequestBody SysLogin syslogin,HttpServletResponse response){
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		String json = teacherglServices.signupNewLogin(syslogin);
		pw.print(json);
		pw.flush();
		pw.close();
		return "xtgl/singupForT";
	}
	/**
	 * ɾ����ʦ
	 */
	@RequestMapping("/deleteTeacher.html")
	public String deleteTeacher(@RequestBody Teacher teacher,HttpServletResponse response){
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		String json = teacherglServices.deleteTeacher(teacher);
		pw.print(json);
		pw.flush();
		pw.close();
		return "xtgl/teachergl";
	}
	
	@Autowired
	private TeacherglServices teacherglServices = null;
	@Autowired
	private CdglServices cdglServices = null;
}
