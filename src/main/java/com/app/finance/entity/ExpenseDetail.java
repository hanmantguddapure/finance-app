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
@Table(name = "expense_detail")
public class ExpenseDetail implements Serializable {

	private static final long serialVersionUID = -5885337441854860677L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long expenseId;
	@ManyToOne(fetch = FetchType.EAGER)
	@PrimaryKeyJoinColumn
	private ExpenseTypes expenseType;
	private LocalDate fromDate;
	private LocalDate toDate;
	private Double amount;
	private String remark;
	private LocalDate lastUpdated;

	public Long getExpenseId() {
		return expenseId;
	}

	public void setExpenseId(Long expenseId) {
		this.expenseId = expenseId;
	}

	public ExpenseTypes getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(ExpenseTypes expenseType) {
		this.expenseType = expenseType;
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

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
