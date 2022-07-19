package com.app.finance.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.app.finance.constant.BaseConstant;
import com.app.finance.dto.BaseResponse;
import com.app.finance.entity.AddressDetail;
import com.app.finance.entity.ContactPersion;
import com.app.finance.entity.CustDetail;
import com.app.finance.entity.CustNomineeDtls;
import com.app.finance.entity.LkpType;
import com.app.finance.entity.LkpValue;
import com.app.finance.exception.DuplicateRecord;
import com.app.finance.exception.RecordNotFound;
import com.app.finance.model.AddressDtls;
import com.app.finance.model.request.CustContactPeopleReq;
import com.app.finance.model.request.CustNomineeRequest;
import com.app.finance.model.request.CustomerDtlsRequest;
import com.app.finance.model.response.CustomerContactPeopleDtls;
import com.app.finance.model.response.CustomerDtsResponse;
import com.app.finance.model.response.CustomerNomineeDtlsResponse;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Transactional
@Service
public class CustomerServiceImpl extends DaoServicess implements CustomerService {

	@Override
	public BaseResponse createNewCustomer(CustomerDtlsRequest customerRequest) {
		log.traceEntry("createNewCustomer(customer){}", customerRequest);
		if (StringUtils.isEmpty(customerRequest.getCustId())) {
			CustDetail customerDtl = this.getDaoManager().getCustomerDao()
					.findByFullName(customerRequest.getFullName().trim());
			if (!Objects.isNull(customerDtl))
				throw new DuplicateRecord("Already customer exist with this name");
		}
		CustDetail custDetail = saveOrUpdateCustomerDetails(customerRequest);

		if (customerRequest.getAddress() != null) {
			customerRequest.setCustId(custDetail.getCustId());
			saveOrUpdateAddressDtls(customerRequest);
		}

		if (null != customerRequest.getContactPeopleDtls()) {
			saveAllContactPeople(customerRequest.getContactPeopleDtls(), custDetail);
		}
		if (null != customerRequest.getNomineeDtls()) {
			saveAllNominee(customerRequest.getNomineeDtls(), custDetail);
		}
		return log.traceExit("createNewCustomer({})",
				BaseResponse.builder().id(custDetail.getCustId()).msg("Created successfully").build());
	}

	public CustomerDtsResponse getCustomerDtlsByCustId(Long custId) {
		AddressDtls cusAddressDtls = null;
		List<CustomerContactPeopleDtls> contactPeoplList = null;
		List<CustomerNomineeDtlsResponse> customerNomineeList = null;
		CustDetail custDetail = getCustomer(custId);
		AddressDetail custAddressDetailEntity = this.getDaoManager().getCustomerDao()
				.getCustAddressDetailByAddressRefId(custDetail.getCustId());
		List<ContactPersion> contactPersionLst = this.getDaoManager().getCustomerDao()
				.findContactPersionsByCustId(custDetail);
		List<CustNomineeDtls> custNomineeDtls = this.getDaoManager().getCustomerDao()
				.findCustNominneesByCustId(custDetail);

		if (null != custAddressDetailEntity)
			cusAddressDtls = AddressDtls.builder().address(custAddressDetailEntity.getAddress())
					.zipCode(custAddressDetailEntity.getZipCode()).email(custAddressDetailEntity.getEmail())
					.phoneNo(custAddressDetailEntity.getPhoneNo1()).altNo(custAddressDetailEntity.getPhoneNo2())
					.nativePlace(custAddressDetailEntity.getNativePlace()).build();
		if (!ObjectUtils.isEmpty(contactPersionLst))
			contactPeoplList = contactPersionLst.stream().map(data -> {
				return CustomerContactPeopleDtls.builder().contactPersionId(data.getContactPersionId())
						.fullName(data.getFullName())
						.address(AddressDtls.builder().address(data.getAddress()).phoneNo(data.getPhone1()).build())
						.build();
			}).collect(Collectors.toList());

		if (!ObjectUtils.isEmpty(custNomineeDtls))
			customerNomineeList = custNomineeDtls.stream().map(data -> {
				return CustomerNomineeDtlsResponse.builder().nomineeId(data.getNomineeId()).fullName(data.getFullName())
						.address(AddressDtls.builder().address(data.getAddress()).phoneNo(data.getPhone()).build())
						.build();
			}).collect(Collectors.toList());

		return CustomerDtsResponse.builder().custId(custDetail.getCustId()).fullName(custDetail.getFullName())
				.profession(custDetail.getProfession()).adharNo(custDetail.getAdharNo()).panNo(custDetail.getPanNo())
				.address(cusAddressDtls).contactPeopleDtls(contactPeoplList).nomineeDtlsResponse(customerNomineeList)
				.build();
	}

	@Override
	public List<CustomerDtsResponse> findAllCustomers() {
		List<CustomerDtsResponse> customerDtsResponses = this.getDaoManager().getCustomerDao().findAllCustomers()
				.stream().map(data -> {
					return CustomerDtsResponse.builder().fullName(data.getFullName()).custId(data.getCustId()).build();
				}).collect(Collectors.toList());
		return customerDtsResponses;
	}

	@Override
	public CustomerDtsResponse editCustomerDetail(CustomerDtlsRequest customerRequest) {
		getCustomer(customerRequest.getCustId());
		CustDetail custDetail = saveOrUpdateCustomerDetails(customerRequest);

		if (customerRequest.getAddress() != null) {
			customerRequest.setCustId(custDetail.getCustId());
			saveOrUpdateAddressDtls(customerRequest);
		}
		return CustomerDtsResponse.builder().custId(customerRequest.getCustId()).build();
	}

	@Override
	public BaseResponse addNewContactPeople(CustomerDtlsRequest customerRequest) {
		if (customerRequest.getCustId() == null)
			throw new NullPointerException(BaseConstant.INVALID_REQUEST);
		CustDetail custDetail = getCustomer(customerRequest.getCustId());
		if (null != customerRequest.getContactPeopleDtls()) {
			saveAllContactPeople(customerRequest.getContactPeopleDtls(), custDetail);
		}
		return BaseResponse.builder().msg(BaseConstant.SUCESS_MSG).id(customerRequest.getCustId()).build();
	}

	@Override
	public BaseResponse editCustContactPeople(CustomerDtlsRequest customerRequest) {
		CustDetail custDetail = getCustomer(customerRequest.getCustId());
		if (null != customerRequest.getContactPeopleDtls()) {
			saveAllContactPeople(customerRequest.getContactPeopleDtls(), custDetail);
		}
		return BaseResponse.builder().msg(BaseConstant.SUCESS_MSG).id(customerRequest.getCustId()).build();
	}

	@Override
	public BaseResponse addCustNomineeDtls(CustomerDtlsRequest request) {
		CustDetail custDetail = getCustomer(request.getCustId());
		if (null != request.getNomineeDtls()) {
			saveAllNominee(request.getNomineeDtls(), custDetail);
		}
		return BaseResponse.builder().msg(BaseConstant.SUCESS_MSG).id(custDetail.getCustId()).build();
	}

	@Override
	public BaseResponse editCustNomineeDtls(CustomerDtlsRequest customerRequest) {
		CustDetail custDetail = getCustomer(customerRequest.getCustId());
		if (null != customerRequest.getNomineeDtls()) {
			saveAllNominee(customerRequest.getNomineeDtls(), custDetail);
		}
		return BaseResponse.builder().msg(BaseConstant.SUCESS_MSG).id(customerRequest.getCustId()).build();
	}

	public CustDetail saveOrUpdateCustomerDetails(CustomerDtlsRequest customerRequest) {
		CustDetail custDetail = CustDetail.builder().custId(customerRequest.getCustId())
				.fullName(customerRequest.getFullName()).shortName(customerRequest.getShortName())
				.profession(customerRequest.getProfession()).adharNo(customerRequest.getAdharNo())
				.panNo(customerRequest.getPanNo()).build();

		return this.getDaoManager().getCustomerDao().saveOrUpdateCustomerDtl(custDetail);
	}

	public void saveOrUpdateAddressDtls(CustomerDtlsRequest customerRequest) {
		AddressDtls addressDtlReq = customerRequest.getAddress();
		LkpType lkpType = this.getDaoManager().getLkpDao().findByLkpTypeName(BaseConstant.LKP_ADDRESS_TYPE);
		LkpValue addressType = this.getDaoManager().getLkpDao()
				.findByLkpValueAndLkpTypeName(BaseConstant.LKP_ADDRESS_TYPE, lkpType);
		Long addressId = null;
		if (!StringUtils.isEmpty(customerRequest.getCustId())) {
			AddressDetail custAddressDetailEntity = this.getDaoManager().getCustomerDao()
					.getCustAddressDetailByAddressRefId(customerRequest.getCustId());
			if (!StringUtils.isEmpty(custAddressDetailEntity.getAddressId()))
				addressId = custAddressDetailEntity.getAddressId();
		}
		AddressDetail addressDetail = AddressDetail.builder().addressId(addressId).address(addressDtlReq.getAddress())
				.zipCode(addressDtlReq.getZipCode()).email(addressDtlReq.getEmail())
				.phoneNo1(addressDtlReq.getPhoneNo()).nativePlace(addressDtlReq.getNativePlace())
				.addressType(addressType).addressRefId(customerRequest.getCustId()).build();
		addressDetail = this.getDaoManager().getCustomerDao().saveOrUpdateAddressDetail(addressDetail);
	}

	public void saveAllContactPeople(List<CustContactPeopleReq> list, CustDetail custDetail) {
		List<ContactPersion> contactPeopleDtls = list.stream().map(data -> {
			return ContactPersion.builder().fullName(data.getFullName()).custId(custDetail)
					.contactPersionId(data.getContactPersionId()).profession(data.getProfession())
					.address(data.getAddress().getAddress()).phone1(data.getAddress().getPhoneNo()).build();
		}).collect(Collectors.toList());
		this.getDaoManager().getCustomerDao().saveAll(contactPeopleDtls);
	}

	public void saveAllNominee(List<CustNomineeRequest> list, CustDetail custDetail) {
		List<CustNomineeDtls> custNomineeDtls = list.stream().map(data -> {
			return CustNomineeDtls.builder().custId(custDetail).nomineeId(data.getNomineeId())
					.fullName(data.getFullName()).build();
		}).collect(Collectors.toList());

		this.getDaoManager().getCustomerDao().saveAllNominees(custNomineeDtls);
	}

	public CustDetail getCustomer(Long id) {
		Optional<CustDetail> customerDtlOptional = this.getDaoManager().getCustomerDao().findCustomerDtlById(id);
		if (!customerDtlOptional.isPresent())
			throw new RecordNotFound(RecordNotFound.Errors.RECORD_NOT_FOUND.name());
		return customerDtlOptional.get();
	}

	/*
	 * @Override public String deleteCustomer(Long custId) {
	 * 
	 * if (this.getDaoManager().getCustomerDao().deleteCustomer(custId, true) == 1)
	 * { PersionDetail cust = new PersionDetail(); cust.setPersionId(custId);
	 * CustomerAudit custAudit =
	 * this.getDaoManager().getCustomerDao().findByCustomer(cust);
	 * custAudit.setLastUpdatedDatetime(LocalDateTime.now());
	 * custAudit.setLastUpdatedUserDesignation("NA");
	 * custAudit.setLastUpdatedUserName("LastUsername");
	 * custAudit.setLoggedInUserId("1"); custAudit.setAction("Deleted");
	 * this.getDaoManager().getCustomerDao().saveCustomerAudit(custAudit); return
	 * "Success"; } return "Fail";
	 * 
	 * return null; }
	 */
	/*
	 * @Override public List<CustContactPeopleReq> findContactPersionsByCustId(Long
	 * custId) { if (null == custId) throw new NullPointerException("Data Missing");
	 * Optional<CustDetail> custPersionalDetail =
	 * this.getDaoManager().getCustomerDao().findCustomerDtlById(custId); if
	 * (!custPersionalDetail.isPresent()) throw new
	 * UsernameNotFoundException("Customer Not Found"); List<CustContactPeopleReq>
	 * contactPersion = null; contactPersion =
	 * this.getDaoManager().getCustomerDao().findContactPersionsByCustId(
	 * custPersionalDetail.get()) .stream().map(data -> { CustContactPeopleReq
	 * contactPersionDto = new CustContactPeopleReq();
	 * contactPersionDto.setFullName(data.getFullName());
	 * contactPersionDto.setContactPersionId(data.getContactPersionId());
	 * contactPersionDto.setCustId(data.getPersionId().getCustId());
	 * contactPersionDto.setDesignation(data.getDesignation());
	 * contactPersionDto.getAddress().setAddress(data.getAddress());
	 * contactPersionDto.getAddress().setPhoneNo(data.getPhone1()); return
	 * contactPersionDto; }).collect(Collectors.toList()); return contactPersion; }
	 */

}
