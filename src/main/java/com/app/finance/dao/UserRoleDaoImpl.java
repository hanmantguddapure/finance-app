package com.app.finance.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.finance.entity.UserCredential;
import com.app.finance.entity.UserRole;
import com.app.finance.repositories.UserRoleRepo;

@Repository
public class UserRoleDaoImpl implements UserRoleDao {
	@Autowired
	private UserRoleRepo userRole;

	@Override
	public UserRole getUserRoleById(UserCredential userId) {
		return userRole.findByUserId(userId);
	}

	@Override
	public UserRole saveUserRole(UserRole userRole) {
		return this.userRole.save(userRole);
	}

}
