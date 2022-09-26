package com.app.finance.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "FD_Account")
public class FDAccount implements Serializable {
	private static final long serialVersionUID = 6551791053633572411L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountNo;
	@ManyToOne(fetch = FetchType.LAZY)
	CustDetail custId;
	private Double amount;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDate closingDate;
	private Float iterest;
	private Double iterestAmt;
	private Byte isActive;
	private String remark;

	public Long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}

	public CustDetail getCustId() {
		return custId;
	}

	public void setCustId(CustDetail custId) {
		this.custId = custId;
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

	public Float getIterest() {
		return iterest;
	}

	public void setIterest(Float iterest) {
		this.iterest = iterest;
	}

	public Double getIterestAmt() {
		return iterestAmt;
	}

	public void setIterestAmt(Double iterestAmt) {
		this.iterestAmt = iterestAmt;
	}

	public Byte getIsActive() {
		return isActive;
	}

	public void setIsActive(Byte isActive) {
		this.isActive = isActive;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
