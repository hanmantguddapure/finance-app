package com.app.finance.dao;

import java.util.List;
import java.util.Optional;

import com.app.finance.entity.AddressDetail;
import com.app.finance.entity.ContactPersion;
import com.app.finance.entity.CustDetail;
import com.app.finance.entity.CustNomineeDtls;

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

	CustNomineeDtls saveOrUpdateCustNomineeDtls(CustNomineeDtls custNomineeDtls);

	List<CustNomineeDtls> findCustNominneesByCustId(CustDetail custid);

	Optional<CustNomineeDtls> findCusNomineeDtlsByNomineeId(Long nomineeId);

	public Iterable<ContactPersion> saveAll(List<ContactPersion> contactList);
	
	public Iterable<CustNomineeDtls> saveAllNominees(List<CustNomineeDtls> custNomineeDtls);

}
