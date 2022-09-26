package com.app.finance.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

public class LoanEMIDetailDto implements Serializable {
	private static final long serialVersionUID = -5686658440240146037L;
	@NotNull(message = "loanAccNo may not null")
	private Long loanAccNo;
	@NotNull(message = "payment may not null")
	private Double payment;
	private String paymentMethod;
	@NotNull(message = "loanStartDate may not be null")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = ISO.DATE_TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private String paymentDate;
	private Long paymentId;

	public Long getLoanAccNo() {
		return loanAccNo;
	}

	public void setLoanAccNo(Long loanAccNo) {
		this.loanAccNo = loanAccNo;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public Double getPayment() {
		return payment;
	}

	public void setPayment(Double payment) {
		this.payment = payment;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

}
