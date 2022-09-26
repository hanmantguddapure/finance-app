package com.app.finance.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.app.finance.entity.ShortTermLoan;

public interface ShortTermLoanRepo extends CrudRepository<ShortTermLoan, Long> {
	public List<ShortTermLoan> findByStatus(Byte status);
}
