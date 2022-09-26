package com.app.finance.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import com.app.finance.dto.AuthDetailsDto;
import com.app.finance.entity.UserCredential;
import com.app.finance.entity.UserRole;
import com.app.finance.exception.RecordNotFound;
import com.app.finance.utils.CommanUtil;
import com.app.finance.utils.JwtTokenUtil;

@Service("AuthSericeImpl")
public class AuthServiceImpl extends DaoServicess implements AuthSerivice {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	public AuthDetailsDto authUserName(String userName) {
		UserCredential usercredential = null;
		usercredential = this.getDaoManager().getAuthDao().findByUserName(userName);
		if (usercredential == null)
			throw new RecordNotFound("user name not found");
		{
			usercredential.setToken((new Random()).nextInt(900000) + 100000);
			usercredential.setTokenTime(LocalTime.now());
			this.getDaoManager().getAuthDao().saveUserCredential(usercredential);
			AuthDetailsDto authDto = new AuthDetailsDto();
			authDto.setUserName(userName);
			authDto.setToken(usercredential.getToken());
			return authDto;
		}
	}

	@Override
	public AuthDetailsDto authPassaword(AuthDetailsDto authDetails) {
		UserCredential usercredential = null;
		AuthDetailsDto authDetailDto = null;
		usercredential = this.getDaoManager().getAuthDao().findByUserName(authDetails.getUserName());
		if (usercredential != null && usercredential.getPassword().equals(authDetails.getPassword())) {
			if (Duration.between(usercredential.getTokenTime(), LocalTime.now()).toMinutes() > 5) {
				authDetailDto = new AuthDetailsDto();
				authDetailDto.setStatus("Session time out");
				return authDetailDto;
			} else {
				usercredential.setLastLoginTime(LocalDateTime.now());
				usercredential.setLoggedInUserIpAddress(authDetails.getLoggedInUserIpAddress());
				usercredential = this.getDaoManager().getAuthDao().saveUserCredential(usercredential);
				UserRole userRole = this.getDaoManager().getUserRole().getUserRoleById(usercredential);
				authDetailDto = new AuthDetailsDto();
				authDetailDto.setStatus("Success");
				if (userRole != null)
					authDetailDto.setRoleId(userRole.getRoleId());
				authDetailDto.setUserId(usercredential.getUserId());
				authDetailDto.setFullName(usercredential.getProfileId().getFullName());
				authDetailDto.setJwtToken("Bearer " + CommanUtil.createJwtToken(usercredential.getUserName(),
						usercredential.getPassword(), authenticationManager, jwtTokenUtil));
				// Call servlet context intilizer for initilizing

			}
		} else {
			throw new RecordNotFound("user not found");
		}
		return authDetailDto;
	}

}
