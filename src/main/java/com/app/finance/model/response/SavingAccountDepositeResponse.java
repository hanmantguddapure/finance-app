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
public class SavingAccountDepositeResponse {
	private Long depositeId;
	private Double amount;
	private Short isCredit;// 1-credit 0-debit
	private LocalDate date;
}
