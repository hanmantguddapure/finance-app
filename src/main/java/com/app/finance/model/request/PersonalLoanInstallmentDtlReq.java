package com.app.finance.model.request;

import java.time.LocalDate;

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
public class PersonalLoanInstallmentDtlReq {
	private Long installmentId;
	private Long loanId ;
	private Double installmentAmt;
	private String installmentType;
	private LocalDate installmentDate;
}
