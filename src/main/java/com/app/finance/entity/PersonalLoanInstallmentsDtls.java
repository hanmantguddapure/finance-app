package com.app.finance.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
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
@Table(name = "tbl_personal_loan_installments")
public class PersonalLoanInstallmentsDtls {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long installmentId;
	@ManyToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private PersonalLoan loanId;
	private Double installmentAmt;
	private String installmentType;
	private LocalDate installmentDate;
}
