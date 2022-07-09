package com.app.finance.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
		CustDetail customerDtl = this.getDaoManager().getCustomerDao()
				.findByFullName(customerRequest.getFullName().trim());
		if (!Objects.isNull(customerDtl))
			throw new DuplicateRecord("Already customer exist with this name");

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
		Optional<CustDetail> customerDtlOptional = this.getDaoManager().getCustomerDao().findCustomerDtlById(custId);
		if (!customerDtlOptional.isPresent())
			throw new RecordNotFound(RecordNotFound.Errors.RECORD_NOT_FOUND.name());

		CustDetail custDetail = customerDtlOptional.get();
		AddressDetail custAddressDetailEntity = this.getDaoManager().getCustomerDao()
				.findAddressDetailByCustId(custDetail);
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

		Optional<CustDetail> customerDtlOptional = this.getDaoManager().getCustomerDao()
				.findCustomerDtlById(customerRequest.getCustId());
		if (!customerDtlOptional.isPresent())
			throw new RecordNotFound(RecordNotFound.Errors.RECORD_NOT_FOUND.name());
		CustDetail custDetail = saveOrUpdateCustomerDetails(customerRequest);

		if (customerRequest.getAddress() != null) {
			customerRequest.setCustId(custDetail.getCustId());
			saveOrUpdateAddressDtls(customerRequest);
		}
		return CustomerDtsResponse.builder().custId(customerRequest.getCustId()).build();
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
		AddressDetail addressDetail = AddressDetail.builder().address(addressDtlReq.getAddress())
				.zipCode(addressDtlReq.getZipCode()).email(addressDtlReq.getEmail())
				.phoneNo1(addressDtlReq.getPhoneNo()).nativePlace(addressDtlReq.getNativePlace())
				.addressType(addressType).addressRefId(customerRequest.getCustId()).build();
		addressDetail = this.getDaoManager().getCustomerDao().saveOrUpdateAddressDetail(addressDetail);
	}

	public void saveAllContactPeople(List<CustContactPeopleReq> list, CustDetail custDetail) {
		List<ContactPersion> contactPeopleDtls = list.stream().map(data -> {
			return ContactPersion.builder().fullName(data.getFullName()).custId(custDetail)
					.profession(data.getProfession()).address(data.getAddress().getAddress())
					.phone1(data.getAddress().getPhoneNo()).build();
		}).collect(Collectors.toList());
		this.getDaoManager().getCustomerDao().saveAll(contactPeopleDtls);
	}

	public void saveAllNominee(List<CustNomineeRequest> list, CustDetail custDetail) {
		List<CustNomineeDtls> custNomineeDtls = list.stream().map(data -> {
			return CustNomineeDtls.builder().custId(custDetail).fullName(data.getFullName()).build();
		}).collect(Collectors.toList());

		this.getDaoManager().getCustomerDao().saveAllNominees(custNomineeDtls);
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
	 * @Override public CustomerDtlsRequest findCustomerDtlById(Long custId) {
	 * Optional<CustDetail> customerEntity =
	 * this.getDaoManager().getCustomerDao().findCustomerDtlById(custId); if
	 * (!customerEntity.isPresent()) throw new RecordNotFound("Customer Not Found");
	 * CustomerDtlsRequest customerDto = new CustomerDtlsRequest();
	 * customerDto.setFullName(customerEntity.get().getFullName()); if (null !=
	 * customerEntity.get().getShortName())
	 * customerDto.setShortName(customerEntity.get().getShortName()); if (null !=
	 * customerEntity.get().getCustType())
	 * customerDto.setCustType(customerEntity.get().getCustType()); if (null !=
	 * customerEntity.get().getProfessionName())
	 * customerDto.setProfessionName(customerEntity.get().getProfessionName());
	 * 
	 * customerDto.setCustId(customerEntity.get().getCustId());
	 * 
	 * if (null != customerEntity.get().getPANNo())
	 * customerDto.setPanNo(customerEntity.get().getPANNo());
	 * 
	 * if (null != customerEntity.get().getAdharNo())
	 * customerDto.setAdharNo(customerEntity.get().getAdharNo()); AddressDetail
	 * custAddressDetail = this.getDaoManager().getCustomerDao()
	 * .findAddressDetailByCustId(customerEntity.get()); if (null !=
	 * custAddressDetail) if (null != custAddressDetail.getAddress())
	 * customerDto.getAddress().setAddress(custAddressDetail.getAddress());
	 * 
	 * customerDto.getAddress().setZipCode(String.valueOf(custAddressDetail.
	 * getZipCode())); if (null != custAddressDetail.getEmail())
	 * customerDto.getAddress().setEmail(custAddressDetail.getEmail()); if (null !=
	 * custAddressDetail.getNativePlace())
	 * customerDto.getAddress().setNativePlace(custAddressDetail.getNativePlace());
	 * if (null != custAddressDetail.getPhoneNo1())
	 * customerDto.getAddress().setPhoneNo(custAddressDetail.getPhoneNo1()); return
	 * customerDto; }
	 * 
	 * 
	 *
	 * 
	 * @Override public CustomerDtlsRequest findCustomerDtlById(Long custId) {
	 * Optional<CustDetail> customerEntity =
	 * this.getDaoManager().getCustomerDao().findCustomerDtlById(custId); if
	 * (!customerEntity.isPresent()) throw new RecordNotFound("Customer Not Found");
	 * CustomerDtlsRequest customerDto = new CustomerDtlsRequest();
	 * customerDto.setFullName(customerEntity.get().getFullName()); if (null !=
	 * customerEntity.get().getShortName())
	 * customerDto.setShortName(customerEntity.get().getShortName()); if (null !=
	 * customerEntity.get().getCustType())
	 * customerDto.setCustType(customerEntity.get().getCustType()); if (null !=
	 * customerEntity.get().getProfessionName())
	 * customerDto.setProfessionName(customerEntity.get().getProfessionName());
	 * 
	 * customerDto.setCustId(customerEntity.get().getCustId());
	 * 
	 * if (null != customerEntity.get().getPANNo())
	 * customerDto.setPanNo(customerEntity.get().getPANNo());
	 * 
	 * if (null != customerEntity.get().getAdharNo())
	 * customerDto.setAdharNo(customerEntity.get().getAdharNo()); AddressDetail
	 * custAddressDetail = this.getDaoManager().getCustomerDao()
	 * .findAddressDetailByCustId(customerEntity.get()); if (null !=
	 * custAddressDetail) if (null != custAddressDetail.getAddress())
	 * customerDto.getAddress().setAddress(custAddressDetail.getAddress());
	 * 
	 * customerDto.getAddress().setZipCode(String.valueOf(custAddressDetail.
	 * getZipCode())); if (null != custAddressDetail.getEmail())
	 * customerDto.getAddress().setEmail(custAddressDetail.getEmail()); if (null !=
	 * custAddressDetail.getNativePlace())
	 * customerDto.getAddress().setNativePlace(custAddressDetail.getNativePlace());
	 * if (null != custAddressDetail.getPhoneNo1())
	 * customerDto.getAddress().setPhoneNo(custAddressDetail.getPhoneNo1()); return
	 * customerDto; }
	 * 
	 * 
	 * 
	 * 
	 * @Override public CustContactPeopleReq
	 * saveOrUpdateAddressDetail(CustContactPeopleReq contactPersionDto) { if (null
	 * == contactPersionDto || contactPersionDto.getCustId() == null) throw new
	 * NullPointerException("Data Missing"); Optional<CustDetail>
	 * custPersionalDetail = this.getDaoManager().getCustomerDao()
	 * .findCustomerDtlById(contactPersionDto.getCustId()); if
	 * (!custPersionalDetail.isPresent()) throw new
	 * UsernameNotFoundException("Customer Not Found"); ContactPersion
	 * contactPersions = null; if (contactPersionDto.getContactPersionId() != null)
	 * { Optional<ContactPersion> contactPersion =
	 * this.getDaoManager().getCustomerDao()
	 * .findContactPersionById(contactPersionDto.getContactPersionId());
	 * contactPersions = contactPersion.get(); } else { contactPersions = new
	 * ContactPersion(); }
	 * 
	 * if (null != contactPersionDto.getFullName())
	 * contactPersions.setFullName(contactPersionDto.getFullName()); if (null !=
	 * contactPersionDto.getDesignation())
	 * contactPersions.setDesignation(contactPersionDto.getDesignation()); if
	 * (contactPersionDto.getAddress() != null) { if (null !=
	 * contactPersionDto.getAddress().getPhoneNo())
	 * contactPersions.setPhone1(contactPersionDto.getAddress().getPhoneNo());
	 * 
	 * if (null != contactPersionDto.getAddress().getAddress())
	 * contactPersions.setAddress(contactPersionDto.getAddress().getAddress());
	 * 
	 * }
	 * 
	 * contactPersions.setPersionId(custPersionalDetail.get()); contactPersions =
	 * this.getDaoManager().getCustomerDao().saveOrUpdateContactPersion(
	 * contactPersions);
	 * contactPersionDto.setContactPersionId(contactPersions.getContactPersionId());
	 * return contactPersionDto; }
	 * 
	 * 
	 * 
	 * @Override public CustContactPersionDto
	 * editCustContactPersion(CustContactPersionDto contactPersionDto) {
	 * 
	 * if (!contactPersion.isPresent()) throw new
	 * RecordNotFound("Contact Persion Not Found"); ContactPersion contactPersions =
	 * contactPersion.get(); if (null != contactPersionDto.getFullName())
	 * contactPersions.setFullName(contactPersionDto.getFullName()); if (null !=
	 * contactPersionDto.getDesignation())
	 * contactPersions.setDesignation(contactPersionDto.getDesignation()); if
	 * (contactPersionDto.getAddress() != null) { if (null !=
	 * contactPersionDto.getAddress().getPhoneNo())
	 * contactPersions.setPhone1(contactPersionDto.getAddress().getPhoneNo());
	 * 
	 * if (null != contactPersionDto.getAddress().getAddress())
	 * contactPersions.setAddress(contactPersionDto.getAddress().getAddress());
	 * 
	 * }
	 * 
	 * contactPersions =
	 * this.getDaoManager().getCustomerDao().saveOrUpdateContactPersion(
	 * contactPersions);
	 * contactPersionDto.setContactPersionId(contactPersions.getContactPersionId());
	 * if (null != contactPersions) { return contactPersionDto; } return null; }
	 * 
	 * 
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
	 * 
	 * @Override public String saveOrUpdateCustNomineeDtls(CustNomineeRequest
	 * request) { Optional<CustNomineeDtls> custNomineeOptional = null;
	 * CustNomineeDtls custNomineeDtls = new CustNomineeDtls(); if
	 * (!Objects.isNull(request.getNomineeId())) custNomineeOptional =
	 * this.getDaoManager().getCustomerDao()
	 * .findCusNomineeDtlsByNomineeId(request.getNomineeId()); if (null !=
	 * custNomineeOptional && custNomineeOptional.isPresent()) custNomineeDtls =
	 * custNomineeOptional.get(); else { Optional<CustDetail> custPersionalDetail =
	 * this.getDaoManager().getCustomerDao()
	 * .findCustomerDtlById(request.getCustId()); custNomineeDtls = new
	 * CustNomineeDtls(); custNomineeDtls.setCustId(custPersionalDetail.get()); } if
	 * (!StringUtils.isEmpty(request.getFullName()))
	 * custNomineeDtls.setFullName(request.getFullName()); if
	 * (!StringUtils.isEmpty(request.getAddress()))
	 * custNomineeDtls.setAddress(request.getAddress()); if
	 * (!StringUtils.isEmpty(request.getEmail()))
	 * custNomineeDtls.setEmail(request.getEmail()); if
	 * (!StringUtils.isEmpty(request.getPhone()))
	 * custNomineeDtls.setPhone(request.getPhone());
	 * this.getDaoManager().getCustomerDao().saveOrUpdateCustNomineeDtls(
	 * custNomineeDtls); return BaseConstant.SUCESS_MSG; }
	 * 
	 */
}
