package com.app.finance.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.app.finance.entity.CustDetail;
import com.app.finance.entity.CustNomineeDtls;

public interface CustNomineeRepo extends CrudRepository<CustNomineeDtls, Long> {
	public List<CustNomineeDtls> getCustCustNomineeDtlsByCustId(CustDetail custDetail);
}
