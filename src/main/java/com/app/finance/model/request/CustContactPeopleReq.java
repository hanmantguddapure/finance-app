package com.app.finance.model.request;

import javax.validation.constraints.NotBlank;

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
	private Long custId;
	private Long contactPersionId;
	@NotBlank(message = "fullName may not be blank/null")
	private String fullName;
	private String profession;
	private AddressDtls address;
}
