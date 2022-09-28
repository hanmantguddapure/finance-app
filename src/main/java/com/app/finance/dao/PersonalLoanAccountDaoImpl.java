package com.app.finance.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.finance.entity.PersonalLoan;
import com.app.finance.entity.PersonalLoanInstallmentsDtls;
import com.app.finance.repositories.PersonalLoanInstallmentRepo;
import com.app.finance.repositories.PersonalLoanRepo;

@Repository
public class PersonalLoanAccountDaoImpl implements PersonalLoanAccountDao {
	@Autowired
	PersonalLoanRepo personalRepo;
	@Autowired
	PersonalLoanInstallmentRepo personalInstallmentRepo;
	@Override
	public PersonalLoan saveOrUpdate(PersonalLoan personalLoan) {
		return personalRepo.save(personalLoan);
	}
	@Override
	public Optional<PersonalLoan> findById(Long loanAccountNo) {
		return personalRepo.findById(loanAccountNo);
	}
	@Override
	public PersonalLoanInstallmentsDtls saveOrUpdate(PersonalLoanInstallmentsDtls personalLoanInstallmentsDtls) {
		return personalInstallmentRepo.save(personalLoanInstallmentsDtls);
	}
	@Override
	public List<PersonalLoanInstallmentsDtls> findByLoanAccountNumber(PersonalLoan personalLoanAccountNo) {
		return personalInstallmentRepo.findByPersonalLoanAccountNo(personalLoanAccountNo);
	}
}
