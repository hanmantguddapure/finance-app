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
@Table(name="LOAN_EMI_DETAIL")
public class LoanInstallmentsDetail implements Serializable {

	private static final long serialVersionUID = -2237604786927897877L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long paymentId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="loan_Account_No")
	private LoanAccountDetail loanAccouuntNo;
	private Double paymentAmount;
	private LocalDate paymentDate;
	private String paymentMode;
	private Short isDelete;
	public Long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
	public LoanAccountDetail getLoanAccouuntNo() {
		return loanAccouuntNo;
	}
	public void setLoanAccouuntNo(LoanAccountDetail loanAccouuntNo) {
		this.loanAccouuntNo = loanAccouuntNo;
	}
	public Double getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
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
	public Short getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Short isDelete) {
		this.isDelete = isDelete;
	}
	
}
