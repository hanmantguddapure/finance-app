package com.app.finance.utils;

import java.text.DecimalFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class CommanUtil {
	private static final Logger logger = LogManager.getLogger(CommanUtil.class);

	public static String createJwtToken(String userName, String password, AuthenticationManager authenticationManager,
			JwtTokenUtil jwtTokenUtil) {
		logger.info(" :Generating JWT Token Parameters :Username: " + userName + " :Password:" + password);
		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return jwtTokenUtil.generateToken(userName);

	}

	public static Double formatDoubleValue(Double number) {
		DecimalFormat decimalFormat = new DecimalFormat("#.###");
		return Double.valueOf(decimalFormat.format(number));
	}

}
