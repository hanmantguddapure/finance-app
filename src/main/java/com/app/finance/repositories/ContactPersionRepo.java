package com.app.finance.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.app.finance.entity.ContactPersion;
import com.app.finance.entity.CustDetail;

public interface ContactPersionRepo extends CrudRepository<ContactPersion, Long>{
	public List<ContactPersion> getCustContactPersionByCustId(CustDetail custPersionalDetail);

}
