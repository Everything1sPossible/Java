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
		// ��application��Χ��һ��HashSet���������е�session
		HashSet<Object> sessions = (HashSet<Object>)application.getAttribute("sessions");
		if(sessions == null){
			sessions = new HashSet<Object>();
			application.setAttribute("sessions", sessions);
		}
		// �´�����session����ӵ�HashSet����
		sessions.add(session);
		// �����ڱ𴦴�application��Χ��ȡ��sessions����
        // Ȼ��ʹ��sessions.size()��ȡ��ǰ���session������Ϊ������������
	}

	@SuppressWarnings("unchecked")
	@Override
	public void sessionDestroyed(HttpSessionEvent e) {
		HttpSession session = e.getSession();
		ServletContext application = session.getServletContext();
		HashSet<Object> sessions = (HashSet<Object>)application.getAttribute("sessions");
		if(sessions != null){
			// ���ٵ�session����HashSet�����Ƴ�
			sessions.remove(session);
		}
	}
}
