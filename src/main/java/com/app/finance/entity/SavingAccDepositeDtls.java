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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "saving_acc_deposite_dtls")
public class SavingAccDepositeDtls {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long depositeId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_number")
	private SavingAccount accountNumber;
	private Double amount;
	private Short isCredit;// 1-credit 0-debit
	private LocalDate date;
}
