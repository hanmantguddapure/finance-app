package com.app.finance.dto;

public class AppSummaryDto {
	private Double openingBalance;
	private Double expenses;
	private Double firmLoan;
	private Double shortTermLoan;
	private LoanSummaryDto loanSummary;
	private FDSummaryDto fdSummary;

	public LoanSummaryDto getLoanSummary() {
		return loanSummary;
	}

	public void setLoanSummary(LoanSummaryDto loanSummary) {
		this.loanSummary = loanSummary;
	}

	public FDSummaryDto getFdSummary() {
		return fdSummary;
	}

	public void setFdSummary(FDSummaryDto fdSummary) {
		this.fdSummary = fdSummary;
	}

	public Double getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(Double openingBalance) {
		this.openingBalance = openingBalance;
	}

	public Double getExpenses() {
		return expenses;
	}

	public void setExpenses(Double expenses) {
		this.expenses = expenses;
	}

	public Double getFirmLoan() {
		return firmLoan;
	}

	public void setFirmLoan(Double firmLoan) {
		this.firmLoan = firmLoan;
	}

	public Double getShortTermLoan() {
		return shortTermLoan;
	}

	public void setShortTermLoan(Double shortTermLoan) {
		this.shortTermLoan = shortTermLoan;
	}

	

}
