package com.app.finance.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "saving_acc_dtl")
public class SavingAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountNumber;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cust_id")
	private CustDetail custId;
	private Double amount;
	private Float interest;
	private Double intrestAmount;
	private LocalDate startDate;
	private LocalDate closingDate;
	private Short isActive;
	private String remark;
}
