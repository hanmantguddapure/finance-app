package com.app.finance.model.response;

import com.app.finance.model.AddressDtls;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerContactPeopleDtls {
	private Long contactPersionId;
	private String fullName;
	private AddressDtls address;
}
