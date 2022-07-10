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

	public BaseResponse addNewContactPeople(CustomerDtlsRequest customerRequest);

	public BaseResponse editCustContactPeople(CustomerDtlsRequest customerRequest);

	public BaseResponse addCustNomineeDtls(CustomerDtlsRequest request);

	public BaseResponse editCustNomineeDtls(CustomerDtlsRequest request);

	/*
	 * public String deleteCustomer(Long custId);
	 * 
	 * public List<CustContactPeopleReq> findContactPersionsByCustId(Long custId);
	 */

}
