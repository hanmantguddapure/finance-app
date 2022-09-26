package com.app.finance.model.request;

import com.app.finance.model.AddressDtls;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustContactPeopleReq {
	private Long contactPersionId;
	private String fullName;
	private String profession;
	private AddressDtls address;
}
