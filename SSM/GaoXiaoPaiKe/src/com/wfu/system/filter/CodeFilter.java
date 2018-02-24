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
        //����������Ӧ�ַ�����  
        request.setCharacterEncoding(charset);  
        response.setCharacterEncoding(charset);  
        //�����ӵĴ��� ,���get������������         
        HttpServletRequest req = (HttpServletRequest)request;  
          
        if(req.getMethod().equalsIgnoreCase("get")){  
            req = new getHttpRequestWrapper(req,charset);  
        }  
          
//        System.out.println("----����"+config.getFilterName()+"����");  
        //���ݸ�Ŀ��servlet��jsp��ʵ����ʱ��װ����������ã�������ԭʼ��HttpServletRequest����  
        chain.doFilter(req, response);  
          
//        System.out.println("----��Ӧ��"+config.getFilterName()+"����");  
 
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
