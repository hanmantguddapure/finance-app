package com.app.finance.model.response;

import java.util.List;

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
public class CustomerDtsResponse {
	private Long custId;
	private String fullName;
	private String shortName;
	private String profession;
	private String adharNo;
	private String panNo;
	private AddressDtls address;
	private List<CustomerContactPeopleDtls> contactPeopleDtls;
	private List<CustomerNomineeDtlsResponse> nomineeDtlsResponse;
}
