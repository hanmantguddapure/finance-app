package com.app.finance.service;

import java.util.List;

import com.app.finance.dto.BaseResponse;
import com.app.finance.model.request.CustomerDtlsRequest;
import com.app.finance.model.response.CustomerDtsResponse;

public interface CustomerService {

	public BaseResponse createNewCustomer(CustomerDtlsRequest customerRequest);

	public CustomerDtsResponse getCustomerDtlsByCustId(Long custId);

	public List<CustomerDtsResponse> findAllCustomers();

	public CustomerDtsResponse editCustomerDetail(CustomerDtlsRequest customerRequest); 

	/*
	 * public CustomerDtlsRequest findCustomerDtlById(Long custId);
	 * 
	 * public List<CustDetail> findAllCustomers();
	 * 
	 * public String deleteCustomer(Long custId);
	 * 
	 * public CustContactPeopleReq saveOrUpdateAddressDetail(CustContactPeopleReq
	 * contactPersionDto);
	 * 
	 * public CustContactPersionDto editCustContactPersion(CustContactPersionDto
	 * contactPersionDto);
	 * 
	 * public List<CustContactPeopleReq> findContactPersionsByCustId(Long custId);
	 * 
	 * public String saveOrUpdateCustNomineeDtls(CustNomineeRequest request);
	 */

}
