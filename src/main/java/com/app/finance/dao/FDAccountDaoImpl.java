package com.app.finance.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.finance.entity.CustDetail;
import com.app.finance.entity.FDAccount;
import com.app.finance.entity.FDInterest;
import com.app.finance.repositories.FDAccountRepo;
import com.app.finance.repositories.FDInterestRepo;

@Repository
public class FDAccountDaoImpl implements FDAccountDao {
	@Autowired
	FDAccountRepo fdAccountRepo;
	@Autowired
	FDInterestRepo fdInterestRepo;

	@Override
	public FDAccount saveOrUpdateFDAccount(FDAccount fdAccount) {
		return this.fdAccountRepo.save(fdAccount);
	}

	@Override
	public Optional<FDAccount> findByAccountId(Long accountNo) {
		return this.fdAccountRepo.findById(accountNo);
	}

	@Override
	public List<FDAccount> findByCustId(CustDetail custDetail) {
		return fdAccountRepo.findByCustId(custDetail);
	}

	@Override
	public List<FDAccount> findByIsActive(Byte isActive) {
		return this.fdAccountRepo.findByIsActive(isActive);
	}

	@Override
	public Iterable<FDAccount> findAllFDAccounts() {
		return this.fdAccountRepo.findAll();
	}

	@Override
	public FDInterest saveOrUpdateInterest(FDInterest fdInterest) {
		return this.fdInterestRepo.save(fdInterest);
	}

	@Override
	public List<FDInterest> findPaidInterestByFdAccountNo(FDAccount fdAccountId) {
		return fdInterestRepo.findByFdAccountId(fdAccountId);
	}

	@Override
	public List<FDInterest> findPaidInterestByFdAccountNos(List<FDAccount> fdAccountId) {
		return fdInterestRepo.findByFdAccountIdIn(fdAccountId);
	}
	
	@Override
	public List<FDAccount> findByIsActive(Byte isActive,LocalDate fromDate,LocalDate toDate) {
		return this.fdAccountRepo.findByIsActiveAndStartDateBetween(isActive,fromDate,toDate);
	}


}
