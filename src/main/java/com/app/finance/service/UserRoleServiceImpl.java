package com.app.finance.service;

import org.springframework.stereotype.Service;

import com.app.finance.entity.UserCredential;
import com.app.finance.entity.UserRole;

@Service
public class UserRoleServiceImpl extends DaoServicess implements UserRoleService{

	@Override
	public UserRole getUserRoleById(UserCredential userid) {
		return this.getDaoManager().getUserRole().getUserRoleById(userid);
	}

}
