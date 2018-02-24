package com.wfu.services.impl;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wfu.mybatis.mapper.XgmmMapper;
import com.wfu.services.XgmmServices;
import com.wfu.web.domain.SysLogin;

@Service
public class XgmmServicesImpl implements XgmmServices {
	
	private static final Logger logger = LoggerFactory.getLogger(XgmmServicesImpl.class);

	@Override
	public void updatePwd(SysLogin sysLogin,HttpServletResponse response) {
		JSONObject json = new JSONObject();
		PrintWriter pw = null;
		String flag = "1";//修改成功
		try {
			pw = response.getWriter();
			xxglMapper.updatePwd(sysLogin);
			json.put("flag", flag);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			flag = "0";	//修改失败
			json.put("flag", flag);
		}
		pw.print(json.toString());
		pw.flush();
		pw.close();
	}
	
	@Autowired
	private XgmmMapper xxglMapper = null;

}
