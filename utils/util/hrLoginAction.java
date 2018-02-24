package com.dhcc.hr.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.dhcc.bpm.identity.user.po.UserPo;
import com.dhcc.common.struts2.DhccActionSupport;
import com.dhcc.common.util.Constant;
import com.dhcc.common.util.ParamConfigUtil;
import com.dhcc.common.util.SysConfig;
import com.dhcc.login.po.DhccUser;
import com.opensymphony.xwork2.ModelDriven;

public class hrLoginAction extends DhccActionSupport implements ModelDriven{
	
	private UserPo userPo = new UserPo();
	
	@Override
	public UserPo getModel() {
		return userPo;
	}
	
	public String hrInterface()throws Exception{
		response.setCharacterEncoding("utf-8");
		DhccUser user = (DhccUser)request.getSession().getAttribute("DhccUser");
		PrintWriter out = response.getWriter();
		if(user==null){
	    	out.print("<script>parent.location.reload();</script>");
	    	out.flush();
	    	out.close();
	        return null;
	    }
		UserPo po = user.getUserPo();
		String email = po.getUserName().replace('·','_')+"@"+SysConfig.MAILSERVAR_NAME;
		String[] retArr = new String[2];
		String url = "";
		String realhrIp = ParamConfigUtil.getParamConfigByPkey("realhrIp");
		String hrIp = ParamConfigUtil.getParamConfigByPkey("hrIp");
		String hrInterfaceId = ParamConfigUtil.getParamConfigByPkey("hrInterfaceId");
		String hrKey = ParamConfigUtil.getParamConfigByPkey("hrKey");
		try{
	    	HttpPost httppost = new HttpPost("http://"+realhrIp+"/custom/ws/validateSession/"+hrInterfaceId+"/"+hrKey+"");
	    	
		    // 请求参数的组装（MAP - Param）
		    List<NameValuePair> qparams = new ArrayList<NameValuePair>();
			qparams.add(new BasicNameValuePair("empDistinctNo", po.getAlias()));
			qparams.add(new BasicNameValuePair("mobile", po.getMobile()));
			qparams.add(new BasicNameValuePair("email", email));
		    // UrlEncodedFormEntity 实例将会使用URL编码来编码参数
	    	UrlEncodedFormEntity entity = new UrlEncodedFormEntity(qparams, "UTF-8");
	    	httppost.setEntity(entity);
	    	httppost.setHeader("Content-type", "application/x-www-form-urlencoded; charset=utf-8");  
		    
	    	// 请求执行，以及响应数据的获取
			HttpClient httpclient = new DefaultHttpClient(); // 创建 httpclient 对象   这里使用httpclient
	    	HttpResponse apResponse = httpclient.execute(httppost);
	    	if(apResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
	    		Header[] headers = apResponse.getHeaders("Set-Cookie");
	    		for (int i=0;i<headers.length;i++){
	    			String sessionId = headers[i].getValue();
	    			if (sessionId.contains("JSESSIONID")){
	    				retArr[0] = sessionId.substring(0,sessionId.indexOf(";"));
	    			}
	    		}
	    		url = "http://"+hrIp+"/setCookie.do?"+retArr[0]+"&pageName=/empself/perfPlanScoreList.do";
	    	}
	    	out.print(url);
	    	out.flush();
	    	out.close();
	        return null;
	    }catch(Exception e){
	        e.printStackTrace();
	    }
		return null;
	}

}
