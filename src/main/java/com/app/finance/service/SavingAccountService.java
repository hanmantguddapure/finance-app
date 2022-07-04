package com.app.finance.service;

import java.util.List;

import com.app.finance.model.request.SavingAccounReq;
import com.app.finance.model.request.SavingAccountDepositeReq;
import com.app.finance.model.response.SavingAccounResponse;
import com.app.finance.model.response.SavingAccountDepositeResponse;

public interface SavingAccountService {
	public SavingAccounResponse saveOrUpdate(SavingAccounReq savingAccounReq);

	public SavingAccountDepositeResponse saveOrUpdate(SavingAccountDepositeReq savingAccounReq);
	
	public SavingAccounResponse findByAccountNumber(Long accountNumber);
	
	public List<SavingAccounResponse> findAllByStatus(Short isActive);

}
