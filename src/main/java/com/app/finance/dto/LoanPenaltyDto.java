package com.app.finance.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

public class LoanPenaltyDto implements Serializable {
	private static final long serialVersionUID = -3457172107505415443L;
	private Long loanPenaltyId;
	@NotNull(message = "loanAccountId May Not Be Null")
	private Long loanAccountId;
	@NotNull(message = "penaltyAmt May Not Be Null")
	private Double penaltyAmt;
	private LocalDate penaltyDate;
	private String custFullName;
	private String remark;

	public Long getLoanPenaltyId() {
		return loanPenaltyId;
	}

	public void setLoanPenaltyId(Long loanPenaltyId) {
		this.loanPenaltyId = loanPenaltyId;
	}

	public Long getLoanAccountId() {
		return loanAccountId;
	}

	public void setLoanAccountId(Long loanAccountId) {
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

	public String getCustFullName() {
		return custFullName;
	}

	public void setCustFullName(String custFullName) {
		this.custFullName = custFullName;
	}

}
