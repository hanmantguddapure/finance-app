package com.app.finance.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.finance.dao.FirmLoanDao;
import com.app.finance.entity.FirmLoanDtls;
import com.app.finance.entity.ShortTermLoan;
import com.app.finance.repositories.FirmLoanRepo;
import com.app.finance.repositories.ShortTermLoanRepo;

@Repository
public class FirmLoanDaoImpl implements FirmLoanDao {
	@Autowired
	FirmLoanRepo firmLoanRepo;
	@Autowired
	ShortTermLoanRepo shortTermLoanRepo;

	@Override
	public FirmLoanDtls saveOrUpdate(FirmLoanDtls firmLoanDtls) {
		return firmLoanRepo.save(firmLoanDtls);
	}

	@Override
	public Iterable<FirmLoanDtls> findAll() {
		return firmLoanRepo.findAll();
	}

	@Override
	public List<FirmLoanDtls> findAllByStatus(Byte status) {
		return firmLoanRepo.findAllByIsActive(status);
	}

	@Override
	public Optional<FirmLoanDtls> findByFirmLoanId(Long firmLoanId) {
		return firmLoanRepo.findById(firmLoanId);
	}

	@Override
	public ShortTermLoan saveOrUpdate(ShortTermLoan shortTermLoan) {
		return shortTermLoanRepo.save(shortTermLoan);
	}

	@Override
	public Optional<ShortTermLoan> findByShortTermLoanId(Long shortTermLoanId) {
		return shortTermLoanRepo.findById(shortTermLoanId);
	}

	@Override
	public List<ShortTermLoan> findAllShortTermLoanByStatus(Byte status) {
		return shortTermLoanRepo.findByStatus(status);
	}

}
