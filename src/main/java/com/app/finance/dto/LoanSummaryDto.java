package com.app.finance.dto;

public class LoanSummaryDto {
	private Long noOfaccounts;
	private Double loanAmounts;
	private Double princepleAmount;
	private Double loanIntrest;
	private Double processingFees;
	private Double disbursements;
	private Double pendingDisburement;
	private Double penalty;
	private Double collections;
	private int closedAccounts;

	public Long getNoOfaccounts() {
		return noOfaccounts;
	}

	public void setNoOfaccounts(Long noOfaccounts) {
		this.noOfaccounts = noOfaccounts;
	}

	public Double getLoanAmounts() {
		return loanAmounts;
	}

	public void setLoanAmounts(Double loanAmounts) {
		this.loanAmounts = loanAmounts;
	}

	public Double getDisbursements() {
		return disbursements;
	}

	public void setDisbursements(Double disbursements) {
		this.disbursements = disbursements;
	}

	public Double getPenalty() {
		return penalty;
	}

	public void setPenalty(Double penalty) {
		this.penalty = penalty;
	}

	public Double getCollections() {
		return collections;
	}

	public void setCollections(Double collections) {
		this.collections = collections;
	}

	public int getClosedAccounts() {
		return closedAccounts;
	}

	public void setClosedAccounts(int closedAccounts) {
		this.closedAccounts = closedAccounts;
	}

	public Double getPrincepleAmount() {
		return princepleAmount;
	}

	public void setPrincepleAmount(Double princepleAmount) {
		this.princepleAmount = princepleAmount;
	}

	public Double getPendingDisburement() {
		return pendingDisburement;
	}

	public void setPendingDisburement(Double pendingDisburement) {
		this.pendingDisburement = pendingDisburement;
	}

	public Double getLoanIntrest() {
		return loanIntrest;
	}

	public void setLoanIntrest(Double loanIntrest) {
		this.loanIntrest = loanIntrest;
	}

	public Double getProcessingFees() {
		return processingFees;
	}

	public void setProcessingFees(Double processingFees) {
		this.processingFees = processingFees;
	}

}
