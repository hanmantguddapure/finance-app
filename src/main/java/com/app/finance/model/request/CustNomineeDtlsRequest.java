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
public class CustNomineeDtlsRequest {
	private Long nomineeId;
	private Long custId; 
	private String fullName;
	private String address;
	private String email;
	private String phone;
}
