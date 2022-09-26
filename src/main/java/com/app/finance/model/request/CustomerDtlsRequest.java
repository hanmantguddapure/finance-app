package com.app.finance.model.request;

import java.util.List;

import javax.validation.constraints.NotBlank;

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
public class CustomerDtlsRequest {
	private Long custId;
	@NotBlank(message = "fullName May Not Be Null/Empty")
	private String fullName;
	private String shortName;
	private String panNo;
	private String adharNo;
	private String profession;
	private AddressDtls address;
	private List<CustContactPeopleReq> contactPeopleDtls;
	private List<CustNomineeRequest> nomineeDtls;
}
