package com.wfu.system.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;



public class getHttpRequestWrapper extends HttpServletRequestWrapper {

	private String encoding = "UTF-8";
	public getHttpRequestWrapper(HttpServletRequest request,String encoding) {
		super(request);
		this.encoding = encoding;
	}
	
	public String getParameter(String name){
//		System.out.println("=====ʵ����getHttpRequestWrapper=====");
		String value = super.getParameter(name);
//		System.out.println("����ǰ����"+name+"��"+value);
		value = value == null ? null : transcoding(value);
//		System.out.println("��������"+name+"��"+value);
		return value;
	}
	
	public String transcoding(String value){
		try {
			return new String(value.getBytes("iso8859-1"), encoding);
		} 
		catch (Exception e) {
			return value;
		}
	}
}
