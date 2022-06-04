package com.app.finance.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.finance.dto.CustContactPersionDto;
import com.app.finance.dto.CustomerDto;
import com.app.finance.entity.AddressDetail;
import com.app.finance.entity.ContactPersion;
import com.app.finance.entity.CustDetail;
import com.app.finance.exception.RecordNotFound;
import com.app.finance.exception.DuplicateRecord;
import com.app.finance.service.CustomerService;
import com.app.finance.service.DaoServicess;
import com.app.finance.utils.AppContstants;

@Transactional
@Service
public class CustomerServiceImpl extends DaoServicess implements CustomerService, AppContstants {

	@Override
	public CustomerDto saveOrUpdateCustomerDtl(CustomerDto customer) {
		CustDetail custPersionalDetail = null;
		AddressDetail addressDetail = null;
		CustDetail customerDtl = this.getDaoManager().getCustomerDao().findByFullName(customer.getFullName().trim());
		if (!Objects.isNull(customerDtl))
			throw new DuplicateRecord("Already customer exist with this name");
		if (customer.getCustId() != null) {
			Optional<CustDetail> custDetail = this.getDaoManager().getCustomerDao()
					.findCustomerDtlById(customer.getCustId());
			custPersionalDetail = custDetail.get();
			addressDetail = this.getDaoManager().getCustomerDao().findAddressDetailByCustId(custPersionalDetail);
		} else {
			custPersionalDetail = new CustDetail();
			addressDetail = new AddressDetail();
		}

		custPersionalDetail.setFullName(customer.getFullName().trim());
		if (null != customer.getShortName())
			custPersionalDetail.setShortName(customer.getShortName());
		if (null != customer.getCustType())
			custPersionalDetail.setCustType(customer.getCustType());
		if (null != customer.getProfessionName())
			custPersionalDetail.setProfessionName(customer.getProfessionName());
		if (null != customer.getGstnNo())
			custPersionalDetail.setGSTINNo(customer.getGstnNo());
		if (null != customer.getPanNo())
			custPersionalDetail.setPANNo(customer.getPanNo());
		if (null != customer.getAdharNo())
			custPersionalDetail.setAdharNo(customer.getAdharNo());

		custPersionalDetail = this.getDaoManager().getCustomerDao().saveOrUpdateCustomerDtl(custPersionalDetail);

		if (custPersionalDetail == null)
			throw new NullPointerException("Error while Saving Customer Detail");

		if (customer.getAddress() != null) {
			if (null != customer.getAddress().getAddress())
				addressDetail.setAddress(customer.getAddress().getAddress());

			if (null != customer.getAddress().getZipCode() && !customer.getAddress().getZipCode().isEmpty())
				addressDetail.setZipCode(Integer.valueOf(customer.getAddress().getZipCode()));
			if (null != customer.getAddress().getEmail() && !customer.getAddress().getEmail().isEmpty())
				addressDetail.setEmail(customer.getAddress().getEmail());
			if (null != customer.getAddress().getPhoneNo() && !customer.getAddress().getPhoneNo().isEmpty())
				addressDetail.setPhoneNo1(customer.getAddress().getPhoneNo());
			if (null != customer.getAddress().getNativePlace() && !customer.getAddress().getNativePlace().isEmpty())
				addressDetail.setNativePlace(customer.getAddress().getNativePlace());
			addressDetail.setPersion(custPersionalDetail);
			addressDetail = this.getDaoManager().getCustomerDao().saveOrUpdateAddressDetail(addressDetail);

		}
		customer.setCustId(custPersionalDetail.getCustId());
		return customer;

	}

	/*
	 * @Override public CustomerDto editCustomerDetail(CustomerDto customer) {
	 * 
	 * 
	 * custPersionalDetail =
	 * this.getDaoManager().getCustomerDao().saveOrUpdateCustomerDtl(
	 * custPersionalDetail);
	 * 
	 * if (custPersionalDetail == null) throw new
	 * NullPointerException("Error while Editing Customer Detail");
	 * 
	 * if (customer.getAddress() != null) { AddressDetail addressDetail =
	 * this.getDaoManager().getCustomerDao()
	 * .findAddressDetailByCustId(custPersionalDetail); if (null !=
	 * customer.getAddress().getAddress())
	 * addressDetail.setAddress(customer.getAddress().getAddress());
	 * 
	 * if (null != customer.getAddress().getZipCode() &&
	 * !customer.getAddress().getZipCode().isEmpty())
	 * addressDetail.setZipCode(Integer.valueOf(customer.getAddress().getZipCode()))
	 * ; if (null != customer.getAddress().getEmail() &&
	 * !customer.getAddress().getEmail().isEmpty())
	 * addressDetail.setEmail(customer.getAddress().getEmail()); if (null !=
	 * customer.getAddress().getPhoneNo() &&
	 * !customer.getAddress().getPhoneNo().isEmpty())
	 * addressDetail.setPhoneNo1(customer.getAddress().getPhoneNo()); if (null !=
	 * customer.getAddress().getNativePlace() &&
	 * !customer.getAddress().getNativePlace().isEmpty())
	 * addressDetail.setNativePlace(customer.getAddress().getNativePlace());
	 * addressDetail.setPersion(custPersionalDetail); addressDetail =
	 * this.getDaoManager().getCustomerDao().saveOrUpdateAddressDetail(addressDetail
	 * );
	 * 
	 * } customer.setCustId(custPersionalDetail.getCustId()); return customer; }
	 */

	@Override
	public CustomerDto findCustomerDtlById(Long custId) {
		Optional<CustDetail> customerEntity = this.getDaoManager().getCustomerDao().findCustomerDtlById(custId);
		if (!customerEntity.isPresent())
			throw new RecordNotFound("Customer Not Found");
		CustomerDto customerDto = new CustomerDto();
		customerDto.setFullName(customerEntity.get().getFullName());
		if (null != customerEntity.get().getShortName())
			customerDto.setShortName(customerEntity.get().getShortName());
		if (null != customerEntity.get().getCustType())
			customerDto.setCustType(customerEntity.get().getCustType());
		if (null != customerEntity.get().getProfessionName())
			customerDto.setProfessionName(customerEntity.get().getProfessionName());

		customerDto.setCustId(customerEntity.get().getCustId());

		if (null != customerEntity.get().getPANNo())
			customerDto.setPanNo(customerEntity.get().getPANNo());

		if (null != customerEntity.get().getAdharNo())
			customerDto.setAdharNo(customerEntity.get().getAdharNo());
		AddressDetail custAddressDetail = this.getDaoManager().getCustomerDao()
				.findAddressDetailByCustId(customerEntity.get());
		if (null != custAddressDetail)
			if (null != custAddressDetail.getAddress())
				customerDto.getAddress().setAddress(custAddressDetail.getAddress());

		customerDto.getAddress().setZipCode(String.valueOf(custAddressDetail.getZipCode()));
		if (null != custAddressDetail.getEmail())
			customerDto.getAddress().setEmail(custAddressDetail.getEmail());
		if (null != custAddressDetail.getNativePlace())
			customerDto.getAddress().setNativePlace(custAddressDetail.getNativePlace());
		if (null != custAddressDetail.getPhoneNo1())
			customerDto.getAddress().setPhoneNo(custAddressDetail.getPhoneNo1());
		return customerDto;
	}

	@Override
	public List<CustDetail> findAllCustomers() {
		return this.getDaoManager().getCustomerDao().findAllCustomers();
	}

	@Override
	public String deleteCustomer(Long custId) {
		/*
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
		 */
		return null;
	}

	@Override
	public CustContactPersionDto saveOrUpdateAddressDetail(CustContactPersionDto contactPersionDto) {
		if (null == contactPersionDto || contactPersionDto.getCustId() == null)
			throw new NullPointerException("Data Missing");
		Optional<CustDetail> custPersionalDetail = this.getDaoManager().getCustomerDao()
				.findCustomerDtlById(contactPersionDto.getCustId());
		if (!custPersionalDetail.isPresent())
			throw new UsernameNotFoundException("Customer Not Found");
		ContactPersion contactPersions = null;
		if (contactPersionDto.getContactPersionId() != null) {
			Optional<ContactPersion> contactPersion = this.getDaoManager().getCustomerDao()
					.findContactPersionById(contactPersionDto.getContactPersionId());
			contactPersions = contactPersion.get();
		} else {
			contactPersions = new ContactPersion();
		}

		if (null != contactPersionDto.getFullName())
			contactPersions.setFullName(contactPersionDto.getFullName());
		if (null != contactPersionDto.getDesignation())
			contactPersions.setDesignation(contactPersionDto.getDesignation());
		if (contactPersionDto.getAddress() != null) {
			if (null != contactPersionDto.getAddress().getPhoneNo())
				contactPersions.setPhone1(contactPersionDto.getAddress().getPhoneNo());

			if (null != contactPersionDto.getAddress().getAddress())
				contactPersions.setAddress(contactPersionDto.getAddress().getAddress());

		}

		contactPersions.setPersionId(custPersionalDetail.get());
		contactPersions = this.getDaoManager().getCustomerDao().saveOrUpdateContactPersion(contactPersions);
		contactPersionDto.setContactPersionId(contactPersions.getContactPersionId());
		if (null != contactPersions) {
			return contactPersionDto;
		}
		return null;

	}

	/*
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
	 */

	@Override
	public List<CustContactPersionDto> findContactPersionsByCustId(Long custId) {
		if (null == custId)
			throw new NullPointerException("Data Missing");
		Optional<CustDetail> custPersionalDetail = this.getDaoManager().getCustomerDao().findCustomerDtlById(custId);
		if (!custPersionalDetail.isPresent())
			throw new UsernameNotFoundException("Customer Not Found");
		List<CustContactPersionDto> contactPersion = null;
		contactPersion = this.getDaoManager().getCustomerDao().findContactPersionsByCustId(custPersionalDetail.get())
				.stream().map(data -> {
					CustContactPersionDto contactPersionDto = new CustContactPersionDto();
					contactPersionDto.setFullName(data.getFullName());
					contactPersionDto.setContactPersionId(data.getContactPersionId());
					contactPersionDto.setCustId(data.getPersionId().getCustId());
					contactPersionDto.setDesignation(data.getDesignation());
					contactPersionDto.getAddress().setAddress(data.getAddress());
					contactPersionDto.getAddress().setPhoneNo(data.getPhone1());
					return contactPersionDto;
				}).collect(Collectors.toList());
		return contactPersion;
	}

}
