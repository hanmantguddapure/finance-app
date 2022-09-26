package com.app.finance.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class LoanCollectionRepo implements Serializable {
	private static final long serialVersionUID = 5056423815416937702L;
	private String fullName;
	private Double payment;
	private Long accNo;
	private LocalDate paymentDate;
	private Double pendingCollection;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Double getPayment() {
		return payment;
	}

	public void setPayment(Double payment) {
		this.payment = payment;
	}

	public Long getAccNo() {
		return accNo;
	}

	public void setAccNo(Long accNo) {
		this.accNo = accNo;
	}

	public Double getPendingCollection() {
		return pendingCollection;
	}

	public void setPendingCollection(Double pendingCollection) {
		this.pendingCollection = pendingCollection;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

}
