package com.wfu.web.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wfu.services.KcglServices;
import com.wfu.web.domain.Major;
import com.wfu.web.domain.MajorCourse;

@Controller
public class KcglController {
	
	private static final Logger logger = LoggerFactory.getLogger(KcglController.class);
	
	/**
	 * ��һ�ν���רҵ�γ̵���
	 */
	@RequestMapping("/courseForMajorView.html")
	public String courseForMajorView(){
		return "kcgl/courseForMajor";
	}
	
	/**
	 * ��ȡרҵ
	 */
	@RequestMapping("/getMajorList.html")
	public String getMajorList(@RequestBody Major major,HttpServletResponse response){
		PrintWriter pw = null ;
		try {
			pw = response.getWriter();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		String json = kcglServices.getMajorList(major.getDepartment());
		pw.print(json);
		pw.flush();
		pw.close();
		return "kcgl/courseForMajor";
	}
	
	/**
	 *��ȡ�γ� 
	 */
	@RequestMapping("/getCourseListForKcgl.html")
	public String getCourseList(HttpServletResponse response){
		PrintWriter pw = null ;
		try {
			pw = response.getWriter();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		String json = kcglServices.getCourseList();
		pw.print(json);
		pw.flush();
		pw.close();
		return "kcgl/courseForMajor";
	}
	/**
	 * ���רҵ�γ��м��
	 */
	@RequestMapping("/addMajorCourse.html")
	public String addMajorCourse(@RequestBody MajorCourse majorCourse,HttpServletResponse response){
		PrintWriter pw = null ;
		try {
			pw = response.getWriter();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		String json = kcglServices.addOrUpdateMajorCourse(majorCourse);
		pw.print(json);
		pw.flush();
		pw.close();
		return "kcgl/courseForMajor";
	}
	
	@Autowired
	private KcglServices kcglServices = null;

}
