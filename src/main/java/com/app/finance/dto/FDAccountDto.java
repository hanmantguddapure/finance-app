package com.app.finance.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FDAccountDto implements Serializable {

	private static final long serialVersionUID = 4692901498567094327L;
	private Long accountNo;
	@NotNull(message = "custId required")
	private Long custId;
	private String custName;
	@NotNull(message = "amount required")
	private Double amount;
	@NotNull(message = "startDate required")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = ISO.DATE_TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	@NotNull(message = "endDate required")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = ISO.DATE_TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate endDate;
	private LocalDate closingDate;
	@NotNull(message = "iterest required")
	private Float interest;
	private Double interestAmt;
	private String isActive;
	private String remark;
	private LocalDate interstPayFrom;
	private LocalDate interestPayTo;
	private Integer pendingMonthsOfInterest;
	private Double pendingInterestAmt;
	private List<FDInterestDto> paidInterestHistory;

	public Long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public LocalDate getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(LocalDate closingDate) {
		this.closingDate = closingDate;
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

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public LocalDate getInterstPayFrom() {
		return interstPayFrom;
	}

	public void setInterstPayFrom(LocalDate interstPayFrom) {
		this.interstPayFrom = interstPayFrom;
	}

	public List<FDInterestDto> getPaidInterestHistory() {
		return paidInterestHistory;
	}

	public void setPaidInterestHistory(List<FDInterestDto> paidInterestHistory) {
		this.paidInterestHistory = paidInterestHistory;
	}

	public Integer getPendingMonthsOfInterest() {
		return pendingMonthsOfInterest;
	}

	public void setPendingMonthsOfInterest(Integer pendingMonthsOfInterest) {
		this.pendingMonthsOfInterest = pendingMonthsOfInterest;
	}

	public LocalDate getInterestPayTo() {
		return interestPayTo;
	}

	public void setInterestPayTo(LocalDate interestPayTo) {
		this.interestPayTo = interestPayTo;
	}

	public Double getPendingInterestAmt() {
		return pendingInterestAmt;
	}

	public void setPendingInterestAmt(Double pendingInterestAmt) {
		this.pendingInterestAmt = pendingInterestAmt;
	}

}
