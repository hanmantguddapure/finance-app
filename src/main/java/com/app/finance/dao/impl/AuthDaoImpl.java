package com.app.finance.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.finance.dao.AuthDao;
import com.app.finance.entity.UserCredential;
import com.app.finance.repositories.AuthRepositories;

@Repository("AuthDaoImpl")
public class AuthDaoImpl implements AuthDao {
	@Autowired
	AuthRepositories authrepo;
	@Override
	public UserCredential findByUserName(String userName) {
		return authrepo.findByUserName(userName);
	}
	
	public UserCredential saveUserCredential(UserCredential usercredential) {
		return authrepo.save(usercredential);
	}

}
