package com.app.finance.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

public class LoanAccountDetailDto implements Serializable {

	private static final long serialVersionUID = -2282881913238864262L;
	private Long loanAccountNo;
	@NotNull(message = "custID may not be null")
	private Long custId;
	@NotNull(message = "principalAmount may not be null")
	private Double principalAmount;
	@NotNull(message = "interest may not be null")
	private Float interest;
	@NotNull(message = "interestAmt may not be null")
	private Double interestAmt;
	@NotNull(message = "depositeAmt may not be null")
	private Double depositeAmt;
	@NotNull(message = "processingFees may not be null")
	private Double processingFees;
	@NotNull(message = "loanAmt may not be null")
	private Double loanAmt;
	@NotNull(message = "loanStartDate may not be null")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = ISO.DATE_TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate loanStartDate;
	@NotNull(message = "loanEndDate may not be null")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = ISO.DATE_TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate loanEndDate;
	@NotNull(message = "installMentType may not be null")
	@NotBlank(message = "installMentType may not be blank")
	private String installMentType;
	@NotNull(message = "installments may not be null")
	private Integer installments;
	@NotNull(message = "installmentAmount may not be null")
	private Double installmentAmount;
	private String custFullName;
	private Double totalCollection;
	private String loanStatus;
	private String remark;
	private Double disburseAmt;
	private String paymentDate;
	private String paymentMode;
	private List<LoanEMIDetailDto> loanCollections;

	public Long getLoanAccountNo() {
		return loanAccountNo;
	}

	public void setLoanAccountNo(Long loanAccountNo) {
		this.loanAccountNo = loanAccountNo;
	}

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public Double getPrincipalAmount() {
		return principalAmount;
	}

	public void setPrincipalAmount(Double principalAmount) {
		this.principalAmount = principalAmount;
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

	public LocalDate getLoanEndDate() {
		return loanEndDate;
	}

	public void setLoanEndDate(LocalDate loanEndDate) {
		this.loanEndDate = loanEndDate;
	}

	public String getInstallMentType() {
		return installMentType;
	}

	public void setInstallMentType(String installMentType) {
		this.installMentType = installMentType;
	}

	public Integer getInstallments() {
		return installments;
	}

	public void setInstallments(Integer installments) {
		this.installments = installments;
	}

	public Double getInstallmentAmount() {
		return installmentAmount;
	}

	public void setInstallmentAmount(Double installmentAmount) {
		this.installmentAmount = installmentAmount;
	}

	public String getCustFullName() {
		return custFullName;
	}

	public void setCustFullName(String custFullName) {
		this.custFullName = custFullName;
	}

	public Double getTotalCollection() {
		return totalCollection;
	}

	public void setTotalCollection(Double totalCollection) {
		this.totalCollection = totalCollection;
	}

	public List<LoanEMIDetailDto> getLoanCollections() {
		return loanCollections;
	}

	public void setLoanCollections(List<LoanEMIDetailDto> loanCollections) {
		this.loanCollections = loanCollections;
	}

	public String getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
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
