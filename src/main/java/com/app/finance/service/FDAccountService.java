package com.app.finance.service;

import java.util.List;

import com.app.finance.dto.FDAccountDto;
import com.app.finance.dto.FDInterestDto;
import com.app.finance.dto.FDSummaryDto;
import com.app.finance.entity.CustDetail;

public interface FDAccountService {
	public FDAccountDto saveorUpdateFDAccount(FDAccountDto fdAccountDto);

	public List<FDAccountDto> findByIsActive(Byte isActive);

	public List<FDAccountDto> findByCustId(Long custId);

	public List<CustDetail> findAllFDHolders();

	public FDAccountDto findByAccountId(Long accountNo);

	public FDAccountDto closeAccount(FDAccountDto fdAccountDto);

	public FDInterestDto saveOrUpdateInterest(FDInterestDto fdInterestDto);

	public FDSummaryDto getFDSummaryByStatus(String status);

}
