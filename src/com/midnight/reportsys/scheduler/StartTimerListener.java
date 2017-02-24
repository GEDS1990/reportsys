package com.midnight.reportsys.scheduler;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.midnight.reportsys.mapper.NoticeMapper;

public class StartTimerListener implements ServletContextListener {

	RemindTimer remindTimer = null;

	public StartTimerListener() {
		super();
	}

	public void contextInitialized(ServletContextEvent event) {
		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
	
		NoticeMapper noticeMapper = (NoticeMapper)wac.getBean("noticeMapper");
		
		
		
		remindTimer = new RemindTimer(noticeMapper);
		remindTimer.executeRemindTimer();
	}

	public void contextDestroyed(ServletContextEvent e) {
	}

}
