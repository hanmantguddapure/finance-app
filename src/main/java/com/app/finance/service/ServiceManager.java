package com.app.finance.service;

public interface ServiceManager {
	public CustomerService getCustomerService();

	public AuthSerivice getAuthService();

	public FirmLoanService getFirmLoanService();

	public LoanAccountService getLoanService();

	public DownloadService getDownloadService();

	public ExpenseService getExpenseService();

	public FDAccountService getFdAccountService();

	public DashBoardService getDashBoardService();

	public SavingAccountService getSavingAccountService();
	
	public PersonalLoanService getPersonalLoanService();
}
