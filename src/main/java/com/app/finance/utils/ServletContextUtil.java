package com.app.finance.utils;

import javax.servlet.ServletContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.app.finance.controller.ControllerManager;

@Component
public class ServletContextUtil extends ControllerManager implements ApplicationListener<ContextRefreshedEvent> {
	private static final Logger logger=LogManager.getLogger(ServletContextUtil.class);
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		logger.info("initilizing servlet context");
	if(servletContext.getAttribute("test")==null) {
			servletContext.setAttribute("test", "test");
		}
		
	}
	
}
