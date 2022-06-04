package com.app.finance.repositories;

import org.springframework.data.repository.CrudRepository;

import com.app.finance.entity.AddressDetail;
import com.app.finance.entity.CustDetail;

public interface AddressRepo extends CrudRepository<AddressDetail, Long> {
	AddressDetail getCustAddressDetailByPersion(CustDetail custPersionalDetail);

}
