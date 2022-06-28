package com.app.finance.service;

import java.util.List;

import com.app.finance.dto.CustContactPersionDto;
import com.app.finance.entity.CustDetail;
import com.app.finance.model.request.CustNomineeDtlsRequest;
import com.app.finance.model.request.CustomerDtlsRequest;
import com.app.finance.model.response.CustomerDtsResponse;

public interface CustomerService {
	public CustomerDtlsRequest saveOrUpdateCustomerDtl(CustomerDtlsRequest customer);

	/* public CustomerDto editCustomerDetail(CustomerDto customer); */

	public CustomerDtlsRequest findCustomerDtlById(Long custId);

	public List<CustDetail> findAllCustomers();

	public String deleteCustomer(Long custId);

	public CustContactPersionDto saveOrUpdateAddressDetail(CustContactPersionDto contactPersionDto);

	/*public CustContactPersionDto editCustContactPersion(CustContactPersionDto contactPersionDto);*/

	public List<CustContactPersionDto> findContactPersionsByCustId(Long custId);
	
	public String saveOrUpdateCustNomineeDtls(CustNomineeDtlsRequest request);
	
	public CustomerDtsResponse getCustomerDtlsByCustId(Long custId);
}
