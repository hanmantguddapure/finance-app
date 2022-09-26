package com.app.finance.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.app.finance.entity.FirmLoanDtls;

public interface FirmLoanRepo extends CrudRepository<FirmLoanDtls, Long> {
	public List<FirmLoanDtls> findAllByIsActive(Byte status);

}
