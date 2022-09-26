package com.app.finance.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "loan_penalty")
public class LoanPenalty implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1826348004611984174L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long loanPenaltyId;
	@PrimaryKeyJoinColumn
	@ManyToOne(fetch = FetchType.LAZY)
	private LoanAccountDetail loanAccountId;
	private Double penaltyAmt;
	private LocalDate penaltyDate;
	private String remark;

	public Long getLoanPenaltyId() {
		return loanPenaltyId;
	}

	public void setLoanPenaltyId(Long loanPenaltyId) {
		this.loanPenaltyId = loanPenaltyId;
	}

	public LoanAccountDetail getLoanAccountId() {
		return loanAccountId;
	}

	public void setLoanAccountId(LoanAccountDetail loanAccountId) {
		this.loanAccountId = loanAccountId;
	}

	public Double getPenaltyAmt() {
		return penaltyAmt;
	}

	public void setPenaltyAmt(Double penaltyAmt) {
		this.penaltyAmt = penaltyAmt;
	}

	public LocalDate getPenaltyDate() {
		return penaltyDate;
	}

	public void setPenaltyDate(LocalDate penaltyDate) {
		this.penaltyDate = penaltyDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
