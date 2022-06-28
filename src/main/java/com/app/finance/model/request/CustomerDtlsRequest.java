package com.app.finance.model.request;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
public class CustomerDtlsRequest implements Serializable {
	private static final long serialVersionUID = 415982864064630044L;
	private Long custId;
	@NotBlank(message = "fullName May Not Be Empty")
	@NotNull(message = "fullName May Not Be Empty")
	private String fullName;
	private String shortName;
	private Short custType;
	private String registrationNo;
	private LocalDate registrationDate;
	private String gstnNo;
	private String panNo;
	private String adharNo;
	private String professionName;
	private AddressDtls address;
}
