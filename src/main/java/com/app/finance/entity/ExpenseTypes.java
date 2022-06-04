package com.app.finance.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "expense_types")
public class ExpenseTypes implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 831363608068488917L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long expenseTypeId;
	@Column(unique=true)
	private String expenseType;
	private LocalDate lastUpodatedDate;

	public Long getExpenseTypeId() {
		return expenseTypeId;
	}

	public void setExpenseTypeId(Long expenseTypeId) {
		this.expenseTypeId = expenseTypeId;
	}

	public String getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
	}

	public LocalDate getLastUpodatedDate() {
		return lastUpodatedDate;
	}

	public void setLastUpodatedDate(LocalDate lastUpodatedDate) {
		this.lastUpodatedDate = lastUpodatedDate;
	}

}
