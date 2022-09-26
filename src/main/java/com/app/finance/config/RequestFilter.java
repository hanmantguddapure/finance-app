package com.app.finance.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component

public class RequestFilter implements Filter {

	/*public ApplicationFilter() {
	
	}*/
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest  request = (HttpServletRequest) req;

		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials","true");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS");
		response.setHeader("Access-Control-Max-Age","3600");
		response.setHeader("Access-Control-Allow-Headers", "Origin, Content-Type, Authorization,X-Requested-With, remeber-me");
		response.setHeader("access-control-expose-headers","Origin, Content-Type, Authorization");

		// Just REPLY OK if request method is OPTIONS for CORS (pre-flight)
		if ( request.getMethod().equals("OPTIONS") ) {
			response.setStatus(200);
        return;
    }
		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig filterConfig) {
	}

	@Override
	public void destroy() {
	}

}