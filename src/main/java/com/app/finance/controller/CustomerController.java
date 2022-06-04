package com.app.finance.controller;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.finance.dto.CustContactPersionDto;
import com.app.finance.dto.CustomerDto;

@RestController
@RequestMapping("/customer")
public class CustomerController extends ControllerManager {
	private final Logger logger = LogManager.getLogger(CustomerController.class);

	@PostMapping("/add-new")
	public ResponseEntity<?> addNew(@Valid @RequestBody CustomerDto customer, Errors error)
			throws MethodArgumentNotValidException {
		logger.info(":Adding New Customer Process Begins--");
		if (error.hasErrors())
			return new ResponseEntity<>(
					error.getAllErrors().stream().map(data -> data.getDefaultMessage()).collect(Collectors.toList()),
					HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<>(this.getServiceManager().getCustomerService().saveOrUpdateCustomerDtl(customer),
				HttpStatus.ACCEPTED);
	}

	@GetMapping("/find-all")
	public ResponseEntity<?> findAll() {
		logger.info(":Find All Customers Preocess Begins--");
		return new ResponseEntity<>(this.getServiceManager().getCustomerService().findAllCustomers(),
				HttpStatus.ACCEPTED);
	}

	@GetMapping("/find-byId/{custId}")
	public ResponseEntity<?> findById(@PathVariable Long custId) {
		logger.info(" : Find Customer By Id Process Begins------");
		if (custId == null)
			throw new NullPointerException("custId Is Empty");
		return new ResponseEntity<>(this.getServiceManager().getCustomerService().findCustomerDtlById(custId),
				HttpStatus.OK);
	}

	@DeleteMapping("/delete/{custId}")
	public ResponseEntity<?> deleteCustomerById(@PathVariable Long custId) {
		if (custId == null)
			throw new NullPointerException(" :Deleting customer--" + custId);
		return ResponseEntity.ok(this.getServiceManager().getCustomerService().deleteCustomer(custId));

	}

	@PutMapping("/edit")
	public ResponseEntity<?> editCustomerDetail(@Valid @RequestBody CustomerDto customer, Errors error) {
		logger.info(":Editing Customer Detail Of--" + customer.getFullName());
		if (error.hasErrors())
			return new ResponseEntity<>(
					error.getAllErrors().stream().map(data -> data.getDefaultMessage()).collect(Collectors.toList()),
					HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<>(this.getServiceManager().getCustomerService().saveOrUpdateCustomerDtl(customer),
				HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/download/{custId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<?> downloadCustPDF(@PathVariable Long custId) throws IOException {
		logger.info(":Downloading PDF Of--" + custId);
		return this.getServiceManager().getDownloadService().customerPdfDownload(custId);

	}

	@PostMapping("/contact-persions/add")
	public ResponseEntity<?> addNewContactPersion(@Valid @RequestBody CustContactPersionDto custContact, Errors error) {
		logger.info(":Adding New Contact Persion Of Customer--" + custContact.getFullName());
		if (error.hasErrors())
			return new ResponseEntity<>(
					error.getAllErrors().stream().map(data -> data.getDefaultMessage()).collect(Collectors.toList()),
					HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<>(
				this.getServiceManager().getCustomerService().saveOrUpdateAddressDetail(custContact),
				HttpStatus.ACCEPTED);
	}

	@GetMapping("/contact-persions/find-by-custId/{custId}")
	public ResponseEntity<?> findAllContactPersionsByCusId(@PathVariable Long custId) {
		logger.info(":Find Contact Persion By Cust Id Process Begins--" + custId);
		if (custId == null)
			throw new NullPointerException("custId Is Empty");
		return new ResponseEntity<>(this.getServiceManager().getCustomerService().findContactPersionsByCustId(custId),
				HttpStatus.OK);
	}

	@PutMapping("/contact-persions/edit")
	public ResponseEntity<?> editContactPersionsDetails(@Valid @RequestBody CustContactPersionDto custContact,
			Errors error) {
		logger.info(":Editing Contact Persion Details--" + custContact.getFullName());
		if (error.hasErrors())
			return new ResponseEntity<>(
					error.getAllErrors().stream().map(data -> data.getDefaultMessage()).collect(Collectors.toList()),
					HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<>(
				this.getServiceManager().getCustomerService().saveOrUpdateAddressDetail(custContact),
				HttpStatus.ACCEPTED);
	}
}
