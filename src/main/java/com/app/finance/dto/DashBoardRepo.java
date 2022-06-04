package com.app.finance.dto;

import java.io.Serializable;

public class DashBoardRepo implements Serializable {
	private static final long serialVersionUID = -6058906565740138063L;
	private Double totalActiveLoanCollection;
	private Long totalOpenedLoanAccount;
	private Double totalRunningLoanAmt;
	private Double totalDisburesements;
	private Double pendingDisbursement;
	private Double pendingCollections;
	private Double todayTotalCollection;
	private Long totalActiveFdAcc;
	private Double totalFdAmount;
	private Double fdPaidInterest;
	private Double pendingFdInterest;

	public Double getTotalActiveLoanCollection() {
		return totalActiveLoanCollection;
	}

	public void setTotalActiveLoanCollection(Double totalActiveLoanCollection) {
		this.totalActiveLoanCollection = totalActiveLoanCollection;
	}

	public Double getTotalRunningLoanAmt() {
		return totalRunningLoanAmt;
	}

	public void setTotalRunningLoanAmt(Double totalRunningLoanAmt) {
		this.totalRunningLoanAmt = totalRunningLoanAmt;
	}

	public Long getTotalOpenedLoanAccount() {
		return totalOpenedLoanAccount;
	}

	public void setTotalOpenedLoanAccount(Long totalOpenedLoanAccount) {
		this.totalOpenedLoanAccount = totalOpenedLoanAccount;
	}

	public Double getPendingCollections() {
		return pendingCollections;
	}

	public void setPendingCollections(Double pendingCollections) {
		this.pendingCollections = pendingCollections;
	}

	public Double getTodayTotalCollection() {
		return todayTotalCollection;
	}

	public void setTodayTotalCollection(Double todayTotalCollection) {
		this.todayTotalCollection = todayTotalCollection;
	}

	public Long getTotalActiveFdAcc() {
		return totalActiveFdAcc;
	}

	public void setTotalActiveFdAcc(Long totalActiveFdAcc) {
		this.totalActiveFdAcc = totalActiveFdAcc;
	}

	public Double getTotalFdAmount() {
		return totalFdAmount;
	}

	public void setTotalFdAmount(Double totalFdAmount) {
		this.totalFdAmount = totalFdAmount;
	}

	public Double getFdPaidInterest() {
		return fdPaidInterest;
	}

	public void setFdPaidInterest(Double fdPaidInterest) {
		this.fdPaidInterest = fdPaidInterest;
	}

	public Double getTotalDisburesements() {
		return totalDisburesements;
	}

	public void setTotalDisburesements(Double totalDisburesements) {
		this.totalDisburesements = totalDisburesements;
	}

	public Double getPendingDisbursement() {
		return pendingDisbursement;
	}

	public void setPendingDisbursement(Double pendingDisbursement) {
		this.pendingDisbursement = pendingDisbursement;
	}

	public Double getPendingFdInterest() {
		return pendingFdInterest;
	}

	public void setPendingFdInterest(Double pendingFdInterest) {
		this.pendingFdInterest = pendingFdInterest;
	}

}
