package com.app.finance.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "expense_types")
public class ExpenseTypes implements Serializable {
	private static final long serialVersionUID = 831363608068488917L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long expenseTypeId;
	@Column(unique = true)
	private String expenseType;
	private LocalDate lastUpodatedDate;
}
