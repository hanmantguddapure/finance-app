package com.app.finance.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDtls{
	private String address;
	private String city;
	private String district;
	private String state;
	private String country;
	private String zipCode;
	private String email;
	private String phoneNo;
	private String nativePlace;
	private String altNo;
}
