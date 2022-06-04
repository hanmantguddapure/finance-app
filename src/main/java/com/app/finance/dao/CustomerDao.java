package com.app.finance.dao;

import java.util.List;
import java.util.Optional;

import com.app.finance.entity.AddressDetail;
import com.app.finance.entity.ContactPersion;
import com.app.finance.entity.CustDetail;

public interface CustomerDao {
	public CustDetail saveOrUpdateCustomerDtl(CustDetail custDetail);

	public Optional<CustDetail> findCustomerDtlById(Long custId);
	
	public CustDetail findByFullName(String fullName);;
	
	public List<CustDetail> findAllCustomers();

	public int deleteCustomer(Long custId, boolean action);

	public AddressDetail saveOrUpdateAddressDetail(AddressDetail addressDetail);

	public AddressDetail findAddressDetailByCustId(CustDetail custId);

	public ContactPersion saveOrUpdateContactPersion(ContactPersion contactPersions);

	public Optional<ContactPersion> findContactPersionById(Long contactPersionId);

	public List<ContactPersion> findContactPersionsByCustId(CustDetail custId);

}
