package com.app.finance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.finance.constant.BaseConstant;
import com.app.finance.dto.BaseResponse;
import com.app.finance.entity.PersonalLoan;
import com.app.finance.entity.PersonalLoanInstallmentsDtls;
import com.app.finance.mapper.PersonalLoanDtlMapper;
import com.app.finance.model.request.PersonalLoanDtlsReq;
import com.app.finance.model.request.PersonalLoanInstallmentDtlReq;
import com.app.finance.model.response.PersonalLoanAccountResponse;
import com.app.finance.model.response.PersonalLoanInstallmentDtl;
import com.app.finance.model.response.Response;

@Service
public class PersonalLoanServiceImpl extends DaoServicess implements PersonalLoanService {

	@Autowired
	PersonalLoanDtlMapper personalLoanDtlMapper;

	@Override
	public BaseResponse saveOrUpdate(PersonalLoanDtlsReq loanDtlsReq) {

		PersonalLoan personalLoan = personalLoanDtlMapper.mapToEntity(loanDtlsReq);
		personalLoan = this.getDaoManager().getPersonalLoanAccountDao().saveOrUpdate(personalLoan);
		return BaseResponse.builder().msg(BaseConstant.SUCESS_MSG).id(personalLoan.getLoanId()).build();

	}

	@Override
	public BaseResponse saveOrUpdate(PersonalLoanInstallmentDtlReq loanDtlsReq) {
		PersonalLoanInstallmentsDtls installmentsDtls = personalLoanDtlMapper
				.mapToPersonalLoanInstallmentEntity(loanDtlsReq);
		Optional<PersonalLoan> personalLoan = this.getDaoManager().getPersonalLoanAccountDao()
				.findById(loanDtlsReq.getLoanId());
		installmentsDtls.setPersonalLoanAccountNo(personalLoan.get());
		installmentsDtls = this.getDaoManager().getPersonalLoanAccountDao().saveOrUpdate(installmentsDtls);
		return BaseResponse.builder().msg(BaseConstant.SUCESS_MSG).id(installmentsDtls.getInstallmentId()).build();
	}

	@Override
	public Response<PersonalLoanAccountResponse> findPersonalLoanAccountDtls(Long personalLoanAccountNumber) {
		PersonalLoanAccountResponse loanAccountResponse = new PersonalLoanAccountResponse();
		Optional<PersonalLoan> personalLoan = this.getDaoManager().getPersonalLoanAccountDao()
				.findById(personalLoanAccountNumber);
		loanAccountResponse = personalLoanDtlMapper.mapToResponse(personalLoan.get());
		List<PersonalLoanInstallmentsDtls> installmentsDtls = this.getDaoManager().getPersonalLoanAccountDao()
				.findByLoanAccountNumber(personalLoan.get());
		//List<PersonalLoanInstallmentDtl> installmentDtls = personalLoanDtlMapper.mapToResponse(installmentsDtls);
		//loanAccountResponse.setInstallmentDtlsLst(installmentDtls);
		return Response.<PersonalLoanAccountResponse>builder().response(loanAccountResponse).build();
	}

}
