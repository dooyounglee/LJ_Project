package com.lj.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class MySessionListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent sessionEvent) {
		HttpSession session = sessionEvent.getSession();
		System.out.println("sessionCreated : "+session.isNew());
		if(session.isNew()) {
			new VisitService().visit();
			session.setAttribute("totalCount", new VisitService().getTotal());
			session.setAttribute("todayCount", new VisitService().getToday());
		}
		System.out.println(session.getId());
		session.setMaxInactiveInterval(60);
		System.out.println(session.getMaxInactiveInterval());
	}
	
	public void sessionDestroyed(HttpSessionEvent sessionEvent) {
		System.out.println("세션 종료");
	}
	
}
