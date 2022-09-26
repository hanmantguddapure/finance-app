package com.app.finance.service;

import java.util.List;

import com.app.finance.dto.FirmLoanDto;
import com.app.finance.dto.ShortTermLoanDto;

public interface FirmLoanService {
	public FirmLoanDto saveOrUpdate(FirmLoanDto firmLoanDtls);

	public FirmLoanDto findFirmLoanDtlById(Long firmLoanId);

	public List<FirmLoanDto> findAllByStatus(Byte status);

	public ShortTermLoanDto saveOrUpdate(ShortTermLoanDto shortTermLoanDto);

	public ShortTermLoanDto findShortTermLoanDtlById(Long shortTermLoanId);

	public List<ShortTermLoanDto> findAllShortTermLoanByStatus(Byte status);

}
