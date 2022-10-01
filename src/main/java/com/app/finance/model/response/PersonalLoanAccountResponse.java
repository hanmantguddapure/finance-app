package com.app.finance.model.response;

import java.time.LocalDate;
import java.util.List;

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
public class PersonalLoanAccountResponse {
	private Long loanId;
	private String loanFrom;
	private Double loanAmt;
	private Double emiAmount;
	private Integer loanTenure;
	private LocalDate emiDate;
	private LocalDate loanStartDate;
	private LocalDate loanEndDate;
	private Float loanInterest;
	private String remark;
	private String loanStatus;
	private List<PersonalLoanInstallmentDtlResp> installmentDtlsLst;
}
