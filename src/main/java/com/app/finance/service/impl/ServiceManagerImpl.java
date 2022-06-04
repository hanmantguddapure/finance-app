package com.app.finance.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.finance.service.AuthSerivice;
import com.app.finance.service.CustomerService;
import com.app.finance.service.DashBoardService;
import com.app.finance.service.DownloadService;
import com.app.finance.service.ExpenseService;
import com.app.finance.service.FDAccountService;
import com.app.finance.service.FirmLoanService;
import com.app.finance.service.LoanAccountService;
import com.app.finance.service.ServiceManager;

@Service
public class ServiceManagerImpl implements ServiceManager {
	@Autowired
	private CustomerService customerService;

	@Autowired
	private AuthSerivice authService;

	@Autowired
	private LoanAccountService loanService;
	@Autowired
	private ExpenseService expenseService;
	@Autowired
	private FDAccountService fdAccountService;
	@Autowired
	private DashBoardService dashBoardService;
	@Autowired
	private DownloadService downloadService;
	@Autowired
	private FirmLoanService firmLoanService;

	public AuthSerivice getAuthService() {
		return authService;
	}

	public void setAuthService(AuthSerivice authService) {
		this.authService = authService;
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustService(CustomerService custService) {
		this.customerService = custService;
	}

	public LoanAccountService getLoanService() {
		return loanService;
	}

	public void setLoanService(LoanAccountService loanService) {
		this.loanService = loanService;
	}

	public DownloadService getDownloadService() {
		return downloadService;
	}

	public void setDownloadService(DownloadService downloadService) {
		this.downloadService = downloadService;
	}

	public ExpenseService getExpenseService() {
		return expenseService;
	}

	public void setExpenseService(ExpenseService expenseService) {
		this.expenseService = expenseService;
	}

	public FDAccountService getFdAccountService() {
		return fdAccountService;
	}

	public void setFdAccountService(FDAccountService fdAccountService) {
		this.fdAccountService = fdAccountService;
	}

	public DashBoardService getDashBoardService() {
		return dashBoardService;
	}

	public void setDashBoardService(DashBoardService dashBoardService) {
		this.dashBoardService = dashBoardService;
	}

	public FirmLoanService getFirmLoanService() {
		return firmLoanService;
	}

	public void setFirmLoanService(FirmLoanService firmLoanService) {
		this.firmLoanService = firmLoanService;
	}

}
