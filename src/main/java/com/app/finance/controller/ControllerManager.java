package com.app.finance.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ServletContextAware;

import com.app.finance.service.ServiceManager;

public class ControllerManager implements ServletContextAware {
	@Autowired
	private ServiceManager serviceManager;
	protected ServletContext servletContext;

	public ServiceManager getServiceManager() {
		return serviceManager;
	}

	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}

	@Override
	public void setServletContext(ServletContext servletcontext) {
		this.servletContext = servletcontext;

	}

}
