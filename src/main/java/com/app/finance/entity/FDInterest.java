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
@Table(name = "FD_INTEREST")
public class FDInterest implements Serializable {
	private static final long serialVersionUID = 4854406500968659910L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long interestId;
	@PrimaryKeyJoinColumn
	@ManyToOne(fetch = FetchType.LAZY)
	private FDAccount fdAccountId;
	private Double paidInterest;
	private LocalDate fromDate;
	private LocalDate toDate;
	private LocalDate paidDate;
	private String paidMode;

	public Long getInterestId() {
		return interestId;
	}

	public void setInterestId(Long interestId) {
		this.interestId = interestId;
	}

	public FDAccount getFdAccountId() {
		return fdAccountId;
	}

	public void setFdAccountId(FDAccount fdAccountId) {
		this.fdAccountId = fdAccountId;
	}

	public Double getPaidInterest() {
		return paidInterest;
	}

	public void setPaidInterest(Double paidInterest) {
		this.paidInterest = paidInterest;
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

	public LocalDate getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(LocalDate paidDate) {
		this.paidDate = paidDate;
	}

	public String getPaidMode() {
		return paidMode;
	}

	public void setPaidMode(String paidMode) {
		this.paidMode = paidMode;
	}

}
