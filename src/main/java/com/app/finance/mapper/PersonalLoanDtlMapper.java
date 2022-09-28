package com.app.finance.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.app.finance.entity.PersonalLoan;
import com.app.finance.entity.PersonalLoanInstallmentsDtls;
import com.app.finance.model.request.PersonalLoanDtlsReq;
import com.app.finance.model.request.PersonalLoanInstallmentDtlReq;
import com.app.finance.model.response.PersonalLoanAccountResponse;
import com.app.finance.model.response.PersonalLoanInstallmentDtl;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PersonalLoanDtlMapper {
	PersonalLoan mapToEntity(PersonalLoanDtlsReq request);

	PersonalLoanInstallmentsDtls mapToPersonalLoanInstallmentEntity(PersonalLoanInstallmentDtlReq request);

	PersonalLoanAccountResponse mapToResponse(PersonalLoan request);

	/*
	 * @Mapping(source = "personalLoanAccountNo.loanId",target =
	 * "personalLoanAccountNo") List<PersonalLoanInstallmentDtl>
	 * mapToResponse(List<PersonalLoanInstallmentsDtls> request);
	 */

}
