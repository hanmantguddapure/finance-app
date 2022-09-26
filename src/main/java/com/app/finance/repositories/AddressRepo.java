package com.app.finance.repositories;

import org.springframework.data.repository.CrudRepository;

import com.app.finance.entity.AddressDetail;

public interface AddressRepo extends CrudRepository<AddressDetail, Long> {
	AddressDetail getCustAddressDetailByAddressRefId(Long custId);

}
