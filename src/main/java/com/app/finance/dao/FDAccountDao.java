package com.app.finance.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.app.finance.entity.CustDetail;
import com.app.finance.entity.FDAccount;
import com.app.finance.entity.FDInterest;

public interface FDAccountDao {
	public FDAccount saveOrUpdateFDAccount(FDAccount fdAccount);

	public Optional<FDAccount> findByAccountId(Long accountNo);

	public List<FDAccount> findByCustId(CustDetail custDetail);

	public Iterable<FDAccount> findAllFDAccounts();

	public List<FDAccount> findByIsActive(Byte isActive);

	public List<FDAccount> findByIsActive(Byte isActive, LocalDate fromDate, LocalDate toDate);

	public FDInterest saveOrUpdateInterest(FDInterest fdInterest);

	public List<FDInterest> findPaidInterestByFdAccountNo(FDAccount fdAccountId);

	public List<FDInterest> findPaidInterestByFdAccountNos(List<FDAccount> fdAccountId);

}
