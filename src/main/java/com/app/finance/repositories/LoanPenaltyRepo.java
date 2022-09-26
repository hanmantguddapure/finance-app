package com.app.finance.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.app.finance.entity.LoanAccountDetail;
import com.app.finance.entity.LoanPenalty;

public interface LoanPenaltyRepo extends CrudRepository<LoanPenalty, Long>{
	public List<LoanPenalty> findByLoanAccountId(LoanAccountDetail accountDetail);

	public List<LoanPenalty> findByLoanAccountIdIn(List<LoanAccountDetail> accountDetails);

}
