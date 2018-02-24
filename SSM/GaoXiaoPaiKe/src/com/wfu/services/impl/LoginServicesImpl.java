package com.wfu.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wfu.mybatis.mapper.LoginMapper;
import com.wfu.services.LoginServices;
import com.wfu.system.tools.Base64Util;
import com.wfu.web.domain.SysLogin;

@Service
public class LoginServicesImpl implements LoginServices {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginServicesImpl.class);

	@Override
	public JSONObject checkLogin(SysLogin sysLogin,HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String message = "�û������������!"; //��¼��¼��Ϣ
		String flag = "0";          //��¼�ı�־::>"0":"ʧ��","1":"�ɹ�"
		try {
			//�ж���֤��
			HttpSession session = request.getSession();
			String kaptcha = (String)session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY); 
			if(!sysLogin.getKaptchaCode().equals(kaptcha)){
				message = "��֤�����!";
			}
			else if(sysLogin.getKaptchaCode().equals(kaptcha)){
				Map<String, Object> resultMap = loginMapper.checkLogin(sysLogin);
				if(resultMap != null){
					message = "��¼�ɹ�!";
					flag = "1";
					session.setAttribute("userMap", resultMap);
					//���˵�id���м��ܱ����json����ǰ̨
					json.put("menuId", Base64Util.ToBase64(resultMap.get("loginmenu").toString()));
				}
			}
			json.put("flag", flag);
			json.put("message", message);
			return json;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	@Override
	public void getMenu(String FMenuId, HttpServletRequest request) {
		Map<String,String[][]> menu2Map = new HashMap<String, String[][]>();
		try {
			//�õ�һ���˵�ID����
			String[] menuIds = FMenuId.split(",");
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("menuIds",menuIds);
			//�õ�һ���˵�list
			List<Map<String,Object>> FMenuList = loginMapper.getFMenu(map);
			if(FMenuList != null){
				for(int i = 0 ; i < FMenuList.size();i++){
					//��ȡ�����˵�
					String FMid = FMenuList.get(i).get("menuid").toString();
					List<Map<String,Object>> SMenuList = loginMapper.getSMenu(FMid);//�õ�һ���˵���Ӧ�Ķ����˵�
					String[][] menu2 = new String[SMenuList.size()][3];
					for(int j = 0 ; j < SMenuList.size();j++){
						menu2[j][0] = SMenuList.get(j).get("menuid").toString();
						menu2[j][1] = SMenuList.get(j).get("menuname").toString();
						menu2[j][2] = SMenuList.get(j).get("menuurl").toString();
					}
					menu2Map.put(FMid, menu2);
				}
			}
			//����Ӧ��һ���˵�������װ��Map���request
			request.setAttribute("FMenuList", FMenuList);
			//��һ���˵����ƶ�Ӧ�Ķ����˵�װ��request
			request.setAttribute("menu2Map", menu2Map);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	@Autowired
	private LoginMapper loginMapper = null;

}
