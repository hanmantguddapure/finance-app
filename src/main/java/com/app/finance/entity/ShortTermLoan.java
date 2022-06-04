package com.app.finance.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "short_term_loan")
public class ShortTermLoan implements Serializable {

	private static final long serialVersionUID = -4493212491902068583L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long shortTermLoanId;
	private String custFullName;
	private Long loanAmt;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDate closingDate;
	private Byte status;
	private String remark;

	public Long getShortTermLoanId() {
		return shortTermLoanId;
	}

	public void setShortTermLoanId(Long shortTermLoanId) {
		this.shortTermLoanId = shortTermLoanId;
	}

	public String getCustFullName() {
		return custFullName;
	}

	public void setCustFullName(String custFullName) {
		this.custFullName = custFullName;
	}

	public Long getLoanAmt() {
		return loanAmt;
	}

	public void setLoanAmt(Long loanAmt) {
		this.loanAmt = loanAmt;
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

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
