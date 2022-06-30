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
public class SavingAccounResponse {
	private Long custId;
	private String customerName;
	private Long accountNumber;
	private Double amount;
	private Float interest;
	private Double interestAmt;
	private LocalDate startDate;
	private LocalDate closingDate;
	private String isActive;
	private List<SavingAccountDepositeResponse> deposite;
}
