package com.app.finance.service;

import com.app.finance.model.request.SavingAccounReq;
import com.app.finance.model.response.SavingAccounResponse;

public interface SavingAccountService {
	public SavingAccounResponse saveOrUpdate(SavingAccounReq savingAccounReq);

}
