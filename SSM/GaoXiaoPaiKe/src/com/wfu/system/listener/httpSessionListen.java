package com.wfu.system.listener;

import java.util.HashSet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class httpSessionListen implements HttpSessionListener {
	

	@SuppressWarnings("unchecked")
	@Override
	public void sessionCreated(HttpSessionEvent e) {
		HttpSession session = e.getSession();
//		String sessionID = session.getId();
//		System.out.println(sessionID);
		ServletContext application = session.getServletContext();
		// 在application范围由一个HashSet集保存所有的session
		HashSet<Object> sessions = (HashSet<Object>)application.getAttribute("sessions");
		if(sessions == null){
			sessions = new HashSet<Object>();
			application.setAttribute("sessions", sessions);
		}
		// 新创建的session均添加到HashSet集中
		sessions.add(session);
		// 可以在别处从application范围中取出sessions集合
        // 然后使用sessions.size()获取当前活动的session数，即为“在线人数”
	}

	@SuppressWarnings("unchecked")
	@Override
	public void sessionDestroyed(HttpSessionEvent e) {
		HttpSession session = e.getSession();
		ServletContext application = session.getServletContext();
		HashSet<Object> sessions = (HashSet<Object>)application.getAttribute("sessions");
		if(sessions != null){
			// 销毁的session均从HashSet集中移除
			sessions.remove(session);
		}
	}
}
