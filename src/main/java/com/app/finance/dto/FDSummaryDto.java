package com.app.finance.dto;

public class FDSummaryDto {
	private Long nofFd;
	private Double fdAmount;
	private Double paidInterest;
	private Double pendingIntrest;
	private int closedFd;
	private Double clodedFDAmount;

	public Long getNofFd() {
		return nofFd;
	}

	public void setNofFd(Long nofFd) {
		this.nofFd = nofFd;
	}

	public Double getFdAmount() {
		return fdAmount;
	}

	public void setFdAmount(Double fdAmount) {
		this.fdAmount = fdAmount;
	}

	public Double getPaidInterest() {
		return paidInterest;
	}

	public void setPaidInterest(Double paidInterest) {
		this.paidInterest = paidInterest;
	}

	public int getClosedFd() {
		return closedFd;
	}

	public void setClosedFd(int closedFd) {
		this.closedFd = closedFd;
	}

	public Double getClodedFDAmount() {
		return clodedFDAmount;
	}

	public void setClodedFDAmount(Double clodedFDAmount) {
		this.clodedFDAmount = clodedFDAmount;
	}

	public Double getPendingIntrest() {
		return pendingIntrest;
	}

	public void setPendingIntrest(Double pendingIntrest) {
		this.pendingIntrest = pendingIntrest;
	}

}
