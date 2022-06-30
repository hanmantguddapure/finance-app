package com.app.finance.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import com.app.finance.entity.AddressDetail;
import com.app.finance.entity.ContactPersion;
import com.app.finance.entity.CustDetail;
import com.app.finance.entity.CustNomineeDtls;
import com.app.finance.repositories.AddressRepo;
import com.app.finance.repositories.ContactPersionRepo;
import com.app.finance.repositories.CustNomineeRepo;
import com.app.finance.repositories.CustomerRepo;

@Repository
public class CustomerDaoImpl implements CustomerDao {
	@Autowired
	CustomerRepo customerRepo;
	@Autowired
	AddressRepo custAddressRepo;
	@Autowired
	ContactPersionRepo custContactpersionRepo;
	@Autowired
	CustNomineeRepo custNomineeRepo;

	@Override
	public CustDetail saveOrUpdateCustomerDtl(CustDetail custDetail) {
		try {
			return customerRepo.save(custDetail);
		} catch (DuplicateKeyException ex) {
			throw new DuplicateKeyException("Record Already Exist");
		}
	}

	public Optional<CustDetail> findCustomerDtlById(Long custId) {
		return customerRepo.findById(custId);
	}

	@Override
	public List<CustDetail> findAllCustomers() {
		return (List<CustDetail>) customerRepo.findAll();

	}

	@Override
	public int deleteCustomer(Long custId, boolean action) {
		return customerRepo.deleteCustomer(custId, action);
	}

	@Override
	public AddressDetail saveOrUpdateAddressDetail(AddressDetail custAddressDetail) {
		return custAddressRepo.save(custAddressDetail);
	}

	@Override
	public AddressDetail findAddressDetailByCustId(CustDetail custId) {
		return custAddressRepo.getCustAddressDetailByPersion(custId);
	}

	@Override
	public ContactPersion saveOrUpdateContactPersion(ContactPersion contactPersions) {
		return custContactpersionRepo.save(contactPersions);
	}

	@Override
	public Optional<ContactPersion> findContactPersionById(Long contactPersionId) {
		return custContactpersionRepo.findById(contactPersionId);
	}

	@Override
	public List<ContactPersion> findContactPersionsByCustId(CustDetail custid) {
		return custContactpersionRepo.getCustContactPersionByPersionId(custid);
	}

	@Override
	public CustDetail findByFullName(String fullName) {
		return customerRepo.findByFullName(fullName);
	}
	
	@Override
	public CustNomineeDtls saveOrUpdateCustNomineeDtls(CustNomineeDtls custNomineeDtls) {
		return custNomineeRepo.save(custNomineeDtls);
	}
	
	@Override
	public List<CustNomineeDtls> findCustNominneesByCustId(CustDetail custid) {
		return custNomineeRepo.getCustCustNomineeDtlsByCustId(custid);
	}
	@Override
	public Optional<CustNomineeDtls> findCusNomineeDtlsByNomineeId(Long nomineeId) {
		return custNomineeRepo.findById(nomineeId);
	}
}
