package com.app.finance.service.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.finance.entity.UserCredential;
import com.app.finance.service.DaoServicess;

@Service(value="userService")
public class UserServiceImpl extends DaoServicess implements UserDetailsService  {
private static final Logger logger=LogManager.getLogger(UserServiceImpl.class);
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		logger.info(":validating user "+userId);
		if(userId==null)
			throw new UsernameNotFoundException("Invalid username or userid");
		UserCredential usercredential = null;
		usercredential = this.getDaoManager().getAuthDao().findByUserName(userId);
		String password=usercredential.getPassword();
		PasswordEncoder passEncode=new BCryptPasswordEncoder();
		String hashedPassword=passEncode.encode(password);
		
		return new org.springframework.security.core.userdetails.User(userId, hashedPassword,getAutherity());
	}
	private List<SimpleGrantedAuthority> getAutherity(){
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

}
