package com.app.finance.dao;

import com.app.finance.entity.UserCredential;
import com.app.finance.entity.UserRole;

public interface UserRoleDao {
	public UserRole getUserRoleById(UserCredential userid);
	public UserRole saveUserRole(UserRole userRole);

}
