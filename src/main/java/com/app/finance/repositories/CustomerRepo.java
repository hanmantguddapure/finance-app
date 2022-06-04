package com.app.finance.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.app.finance.entity.CustDetail;

public interface CustomerRepo extends CrudRepository<CustDetail, Long> {
	@Modifying(clearAutomatically = true)
	@Query("update CustDetail cust SET cust.isDeleted=:action where cust.custId=:custId")
	public int deleteCustomer(@Param("custId") Long custId, @Param("action") boolean action);

	public CustDetail findByFullName(String fullName);
}
