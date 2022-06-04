package com.app.finance.dao;

import com.app.finance.entity.UserCredential;

public interface AuthDao {
	public UserCredential findByUserName(String userName);
	public UserCredential saveUserCredential(UserCredential usercredential);
}
