package com.app.finance.model.request;

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
public class SavingAccountDepositeReq {
	private Long accountNumber;
	private Double amount;
	private Short isCredit;// 1-credit 0-debit
}
