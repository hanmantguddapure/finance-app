package com.app.finance.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Firm_Loans")
public class FirmLoanDtls implements Serializable {

	private static final long serialVersionUID = 1513956227685310363L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long firmLoanId;
	private String loanFrom;
	private Double loanAmt;
	private Double interestAmt;
	private LocalDate startDate;
	private LocalDate closingDate;
	private Byte isActive;
	private String remark;
	private LocalDate lastUpdated;

	public Long getFirmLoanId() {
		return firmLoanId;
	}

	public void setFirmLoanId(Long firmLoanId) {
		this.firmLoanId = firmLoanId;
	}

	public String getLoanFrom() {
		return loanFrom;
	}

	public void setLoanFrom(String loanFrom) {
		this.loanFrom = loanFrom;
	}

	public Double getLoanAmt() {
		return loanAmt;
	}

	public void setLoanAmt(Double loanAmt) {
		this.loanAmt = loanAmt;
	}

	public Double getInterestAmt() {
		return interestAmt;
	}

	public void setInterestAmt(Double interestAmt) {
		this.interestAmt = interestAmt;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(LocalDate closingDate) {
		this.closingDate = closingDate;
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

	public LocalDate getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(LocalDate lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

}
