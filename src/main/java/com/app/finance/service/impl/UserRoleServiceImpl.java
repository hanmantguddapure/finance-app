package com.app.finance.service.impl;

import org.springframework.stereotype.Service;

import com.app.finance.entity.UserCredential;
import com.app.finance.entity.UserRole;
import com.app.finance.service.DaoServicess;
import com.app.finance.service.UserRoleService;

@Service
public class UserRoleServiceImpl extends DaoServicess implements UserRoleService{

	@Override
	public UserRole getUserRoleById(UserCredential userid) {
		return this.getDaoManager().getUserRole().getUserRoleById(userid);
	}

}
