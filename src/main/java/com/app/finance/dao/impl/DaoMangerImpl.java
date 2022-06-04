package com.app.finance.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.finance.dao.AuthDao;
import com.app.finance.dao.CustomerDao;
import com.app.finance.dao.DaoManger;
import com.app.finance.dao.ExpenseDao;
import com.app.finance.dao.FDAccountDao;
import com.app.finance.dao.FirmLoanDao;
import com.app.finance.dao.LoanAccountDao;
import com.app.finance.dao.UserRoleDao;

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
	

}
