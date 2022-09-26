package com.app.finance.dao;

import java.util.List;
import java.util.Optional;

import com.app.finance.entity.FirmLoanDtls;
import com.app.finance.entity.ShortTermLoan;

public interface FirmLoanDao {
	public FirmLoanDtls saveOrUpdate(FirmLoanDtls firmLoanDtls);

	public Optional<FirmLoanDtls> findByFirmLoanId(Long firmLoanId);

	public Iterable<FirmLoanDtls> findAll();

	public List<FirmLoanDtls> findAllByStatus(Byte status);

	public ShortTermLoan saveOrUpdate(ShortTermLoan shortTermLoan);

	public Optional<ShortTermLoan> findByShortTermLoanId(Long shortTermLoanId);

	public List<ShortTermLoan> findAllShortTermLoanByStatus(Byte status);

}
