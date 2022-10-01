package com.app.finance.service;

import com.app.finance.dto.BaseResponse;
import com.app.finance.model.request.PersonalLoanDtlsReq;
import com.app.finance.model.request.PersonalLoanInstallmentDtlReq;
import com.app.finance.model.response.PersonalLoanAccountResponse;
import com.app.finance.model.response.Response;

public interface PersonalLoanService {
	public BaseResponse saveOrUpdate(PersonalLoanDtlsReq loanDtlsReq);

	public BaseResponse saveOrUpdate(PersonalLoanInstallmentDtlReq loanDtlsReq);

	public Response<PersonalLoanAccountResponse> findPersonalLoanAccountDtls(Long loanId);

}
