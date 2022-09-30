package com.app.finance.entity;

import java.time.LocalDate;

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
@Table(name = "tbl_personal_loan")
public class PersonalLoan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long personalLoanAccountNo;
	private String loanFrom;
	private Double loanAmt;
	private Double monthlyEMIAmt;
	private Integer loanTenure;
	private LocalDate montlyEMIDate;
	private LocalDate loanStartDate;
	private LocalDate loanEndDate;
	private Float loanInterest;
	private String remark;
	private String loanStatus;
}
