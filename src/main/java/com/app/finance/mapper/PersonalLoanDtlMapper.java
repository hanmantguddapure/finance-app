package com.app.finance.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.app.finance.entity.PersonalLoan;
import com.app.finance.entity.PersonalLoanInstallmentsDtls;
import com.app.finance.model.request.PersonalLoanDtlsReq;
import com.app.finance.model.request.PersonalLoanInstallmentDtlReq;
import com.app.finance.model.response.PersonalLoanAccountResponse;
import com.app.finance.model.response.PersonalLoanInstallmentDtlResp;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PersonalLoanDtlMapper {
	PersonalLoan mapToEntity(PersonalLoanDtlsReq request);

	PersonalLoanInstallmentsDtls map(PersonalLoanInstallmentDtlReq request);

	PersonalLoanAccountResponse map(PersonalLoan request);

	List<PersonalLoanInstallmentDtlResp> map(List<PersonalLoanInstallmentsDtls> request);

	default PersonalLoanInstallmentDtlResp map(PersonalLoanInstallmentsDtls employee) {
		PersonalLoanInstallmentDtlResp employeDtlResp = new PersonalLoanInstallmentDtlResp();
		employeDtlResp.setPersonalLoanAccountNo(employee.getPersonalLoanAccountNo().getPersonalLoanAccountNo());
		return employeDtlResp;
	}

}
