package com.app.finance.repositories;

import org.springframework.data.repository.CrudRepository;

import com.app.finance.entity.UserCredential;
import com.app.finance.entity.UserRole;

public interface UserRoleRepo extends CrudRepository<UserRole, Long> {
	public UserRole findByUserId(UserCredential userId);

}
