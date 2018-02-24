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
	 * 第一次进入专业课程调整
	 */
	@RequestMapping("/courseForMajorView.html")
	public String courseForMajorView(){
		return "kcgl/courseForMajor";
	}
	
	/**
	 * 获取专业
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
	 *获取课程 
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
	 * 添加专业课程中间表
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
