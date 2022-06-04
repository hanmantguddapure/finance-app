package com.app.finance.service;

import java.util.List;

import com.app.finance.dto.CustContactPersionDto;
import com.app.finance.dto.CustomerDto;
import com.app.finance.entity.CustDetail;

public interface CustomerService {
	public CustomerDto saveOrUpdateCustomerDtl(CustomerDto customer);

	/* public CustomerDto editCustomerDetail(CustomerDto customer); */

	public CustomerDto findCustomerDtlById(Long custId);

	public List<CustDetail> findAllCustomers();

	public String deleteCustomer(Long custId);

	public CustContactPersionDto saveOrUpdateAddressDetail(CustContactPersionDto contactPersionDto);

	/*public CustContactPersionDto editCustContactPersion(CustContactPersionDto contactPersionDto);*/

	public List<CustContactPersionDto> findContactPersionsByCustId(Long custId);

}
