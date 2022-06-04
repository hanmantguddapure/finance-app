package com.app.finance.dao;

public interface DaoManger {
	public CustomerDao getCustomerDao();

	public AuthDao getAuthDao();

	public LoanAccountDao getLoanSectionDao();

	public UserRoleDao getUserRole();

	public ExpenseDao getExpenseDao();

	public FDAccountDao getFdAccountDao();

	public FirmLoanDao getFirmLoanDao();

}
