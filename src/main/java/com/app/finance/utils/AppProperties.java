package com.app.finance.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
@PropertySource("classpath:application.properties")
public class AppProperties {
	@Value("${allowed-app-urls}")
	private String allowAppUrl;
	@Value("${allowed-sys-urls}")
	private String allowSysUrl;

	public String getAllowAppUrl() {
		return allowAppUrl;
	}

	public void setAllowAppUrl(String allowAppUrl) {
		this.allowAppUrl = allowAppUrl;
	}

	public String getAllowSysUrl() {
		return allowSysUrl;
	}

	public void setAllowSysUrl(String allowSysUrl) {
		this.allowSysUrl = allowSysUrl;
	}

}
