package com.wfu.system.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


public class CodeFilter implements Filter 
{
	private String charset = "UTF-8";
	private FilterConfig config ;
	public void doFilter(ServletRequest request, ServletResponse response,  
            FilterChain chain) throws IOException, ServletException {  
        //设置请求响应字符编码  
        request.setCharacterEncoding(charset);  
        response.setCharacterEncoding(charset);  
        //新增加的代码 ,解决get请求乱码问题         
        HttpServletRequest req = (HttpServletRequest)request;  
          
        if(req.getMethod().equalsIgnoreCase("get")){  
            req = new getHttpRequestWrapper(req,charset);  
        }  
          
//        System.out.println("----请求被"+config.getFilterName()+"过滤");  
        //传递给目标servlet或jsp的实际上时包装器对象的引用，而不是原始的HttpServletRequest对象  
        chain.doFilter(req, response);  
          
//        System.out.println("----响应被"+config.getFilterName()+"过滤");  
 
    }  

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		this.config = arg0;
	}
	
	@Override
	public void destroy() {
		this.config = null;
	}

}
