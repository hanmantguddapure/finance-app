package com.app.finance.service;

import com.app.finance.dto.AuthDetailsDto;
import com.app.finance.entity.UserCredential;

public interface AuthSerivice {
	public AuthDetailsDto authUserName(String userName);
	public AuthDetailsDto  authPassaword(AuthDetailsDto authDetails);
}
