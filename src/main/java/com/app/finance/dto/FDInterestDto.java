package com.app.finance.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FDInterestDto implements Serializable {
	private static final long serialVersionUID = 702725178658959440L;
	private Long interestId;
	@NotNull(message = "fdAccountId may not be null/empty")
	private Long fdAccountId;
	@NotNull(message = "interest amt may not be null/empty")
	private Double interestAmt;
	@NotNull(message = "fromDate required")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = ISO.DATE_TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate fromDate;
	@NotNull(message = "toDate required")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = ISO.DATE_TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate toDate;
	@NotNull(message = "paidMode may not be null")
	private String paidMode;
	private LocalDate paidDate;

	public Long getInterestId() {
		return interestId;
	}

	public void setInterestId(Long interestId) {
		this.interestId = interestId;
	}

	public Long getFdAccountId() {
		return fdAccountId;
	}

	public void setFdAccountId(Long fdAccountId) {
		this.fdAccountId = fdAccountId;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	public Double getInterestAmt() {
		return interestAmt;
	}

	public void setInterestAmt(Double interestAmt) {
		this.interestAmt = interestAmt;
	}

	public String getPaidMode() {
		return paidMode;
	}

	public void setPaidMode(String paidMode) {
		this.paidMode = paidMode;
	}

	public LocalDate getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(LocalDate paidDate) {
		this.paidDate = paidDate;
	}

}
