package com.app.finance.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LOAN_ACCOUNT_DETAIL")
public class LoanAccountDetail implements Serializable {
	private static final long serialVersionUID = 5901789227124639010L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long loanAccountNo;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cust_id")
	private CustDetail custId;
	private Double principalAmount;
	private Float interest;
	private Double interestAmt;
	private Double processingFees;
	private Double depositeAmt;
	private Double loanAmt;
	private LocalDate loanStartDate;
	private LocalDate loanEndDate;
	private LocalDate installmentSartDate;
	private Double disburseAmt;
	private LocalDate paymentDate;
	private String paymentMode;
	private String installMentType;
	private Integer installments;
	private Double installmentAmount;
	private Double totalCollection;
	private String loanStatus;
	private LocalDate lastUpdated;
	private String remark;

	public Long getLoanAccountNo() {
		return loanAccountNo;
	}

	public void setLoanAccountNo(Long loanAccountNo) {
		this.loanAccountNo = loanAccountNo;
	}

	public CustDetail getCustId() {
		return custId;
	}

	public void setCustId(CustDetail custId) {
		this.custId = custId;
	}

	public Double getPrincipalAmount() {
		return principalAmount;
	}

	public void setPrincipalAmount(Double principalAmount) {
		this.principalAmount = principalAmount;
	}

	public LocalDate getInstallmentSartDate() {
		return installmentSartDate;
	}

	public void setInstallmentSartDate(LocalDate installmentSartDate) {
		this.installmentSartDate = installmentSartDate;
	}

	public Double getInstallmentAmount() {
		return installmentAmount;
	}

	public void setInstallmentAmount(Double installmentAmount) {
		this.installmentAmount = installmentAmount;
	}

	public Double getTotalCollection() {
		return totalCollection;
	}

	public void setTotalCollection(Double totalCollection) {
		this.totalCollection = totalCollection;
	}

	public String getInstallMentType() {
		return installMentType;
	}

	public void setInstallMentType(String installMentType) {
		this.installMentType = installMentType;
	}

	public String getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}

	public Float getInterest() {
		return interest;
	}

	public void setInterest(Float interest) {
		this.interest = interest;
	}

	public Double getInterestAmt() {
		return interestAmt;
	}

	public void setInterestAmt(Double interestAmt) {
		this.interestAmt = interestAmt;
	}

	public Double getDepositeAmt() {
		return depositeAmt;
	}

	public void setDepositeAmt(Double depositeAmt) {
		this.depositeAmt = depositeAmt;
	}

	public Double getProcessingFees() {
		return processingFees;
	}

	public void setProcessingFees(Double processingFees) {
		this.processingFees = processingFees;
	}

	public Double getLoanAmt() {
		return loanAmt;
	}

	public void setLoanAmt(Double loanAmt) {
		this.loanAmt = loanAmt;
	}

	public LocalDate getLoanStartDate() {
		return loanStartDate;
	}

	public void setLoanStartDate(LocalDate loanStartDate) {
		this.loanStartDate = loanStartDate;
	}

	public Integer getInstallments() {
		return installments;
	}

	public void setInstallments(Integer installments) {
		this.installments = installments;
	}

	public LocalDate getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(LocalDate lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public LocalDate getLoanEndDate() {
		return loanEndDate;
	}

	public void setLoanEndDate(LocalDate loanEndDate) {
		this.loanEndDate = loanEndDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public Double getDisburseAmt() {
		return disburseAmt;
	}

	public void setDisburseAmt(Double disburseAmt) {
		this.disburseAmt = disburseAmt;
	}

}
