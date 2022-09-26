package com.app.finance.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DaoMangerImpl implements DaoManger {
	@Autowired
	private CustomerDao custMasterDao;
	@Autowired
	private AuthDao authDao;
	@Autowired
	private UserRoleDao userRole;
	@Autowired
	private LoanAccountDao loanSectionDao;
	@Autowired
	private ExpenseDao expenseDao;
	@Autowired
	private FDAccountDao fdAccountDao;
	@Autowired
	private FirmLoanDao firmLoanDao;
	@Autowired
	private SavingAccDao savingAccDao;
	@Autowired
	private LkpDao lkpDao;

	public AuthDao getAuthDao() {
		return authDao;
	}

	public void setAuthDao(AuthDao authDao) {
		this.authDao = authDao;
	}

	public CustomerDao getCustMasterDao() {
		return custMasterDao;
	}

	public CustomerDao getCustomerDao() {
		return custMasterDao;
	}

	public void setCustomerDao(CustomerDao custMasterDao) {
		this.custMasterDao = custMasterDao;
	}

	public LoanAccountDao getLoanSectionDao() {
		return loanSectionDao;
	}

	public void setLoanSectionDao(LoanAccountDao loanSectionDao) {
		this.loanSectionDao = loanSectionDao;
	}

	public UserRoleDao getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRoleDao userRole) {
		this.userRole = userRole;
	}

	public ExpenseDao getExpenseDao() {
		return expenseDao;
	}

	public void setExpenseDao(ExpenseDao expenseDao) {
		this.expenseDao = expenseDao;
	}

	public FDAccountDao getFdAccountDao() {
		return fdAccountDao;
	}

	public void setFdAccountDao(FDAccountDao fdAccountDao) {
		this.fdAccountDao = fdAccountDao;
	}

	public FirmLoanDao getFirmLoanDao() {
		return firmLoanDao;
	}

	public void setFirmLoanDao(FirmLoanDao firmLoanDao) {
		this.firmLoanDao = firmLoanDao;
	}

	public SavingAccDao getSavingAccDao() {
		return savingAccDao;
	}

	public void setSavingAccDao(SavingAccDao savingAccDao) {
		this.savingAccDao = savingAccDao;
	}

	public LkpDao getLkpDao() {
		return lkpDao;
	}

	public void setLkpDao(LkpDao lkpDao) {
		this.lkpDao = lkpDao;
	}

}
