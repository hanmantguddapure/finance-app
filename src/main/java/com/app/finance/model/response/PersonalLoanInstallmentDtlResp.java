package com.app.finance.model.response;

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
public class PersonalLoanInstallmentDtlResp {
	private Long installmentId;
	private Long personalLoanAccountNo;
	private Double installmentAmt;
	private String installmentType;
	private LocalDate installmentDate;
}
