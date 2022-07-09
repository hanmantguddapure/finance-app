package com.app.finance.model.request;

import com.app.finance.model.AddressDtls;

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
public class CustNomineeRequest {
	private Long nomineeId;
	private Long custId;
	private String fullName;
	private AddressDtls address;
}
