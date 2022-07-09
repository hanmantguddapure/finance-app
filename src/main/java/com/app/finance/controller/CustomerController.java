package com.app.finance.controller;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.finance.dto.BaseResponse;
import com.app.finance.model.request.CustomerDtlsRequest;
import com.app.finance.model.response.CustomerDtsResponse;
import com.app.finance.model.response.Response;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/customer")
public class CustomerController extends ControllerManager {

	@PostMapping("/add-new")
	public ResponseEntity<?> addNew(@Valid @RequestBody CustomerDtlsRequest customer, Errors error)
			throws MethodArgumentNotValidException {
		log.traceEntry("addNew(customer){}", customer);
		if (error.hasErrors())
			return new ResponseEntity<>(
					error.getAllErrors().stream().map(data -> data.getDefaultMessage()).collect(Collectors.toList()),
					HttpStatus.NOT_ACCEPTABLE);
		BaseResponse baseResponse = this.getServiceManager().getCustomerService().createNewCustomer(customer);
		Response<BaseResponse> response = Response.<BaseResponse>builder().response(baseResponse)
				.status(HttpStatus.OK.value()).build();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/find-byId/{custId}")
	public ResponseEntity<?> findById(@PathVariable Long custId) {
		log.traceEntry("findById(custId){}", custId);
		if (custId == null)
			throw new NullPointerException("custId Is Empty");
		CustomerDtsResponse customerDtsResponse = this.getServiceManager().getCustomerService()
				.getCustomerDtlsByCustId(custId);
		return new ResponseEntity<>(
				Response.builder().response(customerDtsResponse).status(HttpStatus.OK.value()).build(), HttpStatus.OK);
	}

	/*
	 * @GetMapping("/find-all") public ResponseEntity<?> findAll() {
	 * logger.info(":Find All Customers Preocess Begins--"); return new
	 * ResponseEntity<>(this.getServiceManager().getCustomerService().
	 * findAllCustomers(), HttpStatus.ACCEPTED); }
	 * 
	 * 
	 * @DeleteMapping("/delete/{custId}") public ResponseEntity<?>
	 * deleteCustomerById(@PathVariable Long custId) { if (custId == null) throw new
	 * NullPointerException(" :Deleting customer--" + custId); return
	 * ResponseEntity.ok(this.getServiceManager().getCustomerService().
	 * deleteCustomer(custId));
	 * 
	 * }
	 * 
	 * @PutMapping("/edit") public ResponseEntity<?>
	 * editCustomerDetail(@Valid @RequestBody CustomerDtlsRequest customer, Errors
	 * error) { logger.info(":Editing Customer Detail Of--" +
	 * customer.getFullName()); if (error.hasErrors()) return new ResponseEntity<>(
	 * error.getAllErrors().stream().map(data ->
	 * data.getDefaultMessage()).collect(Collectors.toList()),
	 * HttpStatus.NOT_ACCEPTABLE); return new
	 * ResponseEntity<>(this.getServiceManager().getCustomerService().
	 * saveOrUpdateCustomerDtl(customer), HttpStatus.ACCEPTED); }
	 * 
	 * @RequestMapping(value = "/download/{custId}", method = RequestMethod.GET,
	 * produces = MediaType.APPLICATION_PDF_VALUE) public ResponseEntity<?>
	 * downloadCustPDF(@PathVariable Long custId) throws IOException {
	 * logger.info(":Downloading PDF Of--" + custId); return
	 * this.getServiceManager().getDownloadService().customerPdfDownload(custId);
	 * 
	 * }
	 * 
	 * @PostMapping("/contact-persions/add") public ResponseEntity<?>
	 * addNewContactPersion(@Valid @RequestBody CustContactPeopleReq custContact,
	 * Errors error) { logger.info(":Adding New Contact Persion Of Customer--" +
	 * custContact.getFullName()); if (error.hasErrors()) return new
	 * ResponseEntity<>( error.getAllErrors().stream().map(data ->
	 * data.getDefaultMessage()).collect(Collectors.toList()),
	 * HttpStatus.NOT_ACCEPTABLE); return new ResponseEntity<>(
	 * this.getServiceManager().getCustomerService().saveOrUpdateAddressDetail(
	 * custContact), HttpStatus.ACCEPTED); }
	 * 
	 * @GetMapping("/contact-persions/find-by-custId/{custId}") public
	 * ResponseEntity<?> findAllContactPersionsByCusId(@PathVariable Long custId) {
	 * logger.info(":Find Contact Persion By Cust Id Process Begins--" + custId); if
	 * (custId == null) throw new NullPointerException("custId Is Empty"); return
	 * new ResponseEntity<>(this.getServiceManager().getCustomerService().
	 * findContactPersionsByCustId(custId), HttpStatus.OK); }
	 * 
	 * @PutMapping("/contact-persions/edit") public ResponseEntity<?>
	 * editContactPersionsDetails(@Valid @RequestBody CustContactPeopleReq
	 * custContact, Errors error) { logger.info(":Editing Contact Persion Details--"
	 * + custContact.getFullName()); if (error.hasErrors()) return new
	 * ResponseEntity<>( error.getAllErrors().stream().map(data ->
	 * data.getDefaultMessage()).collect(Collectors.toList()),
	 * HttpStatus.NOT_ACCEPTABLE); return new ResponseEntity<>(
	 * this.getServiceManager().getCustomerService().saveOrUpdateAddressDetail(
	 * custContact), HttpStatus.ACCEPTED); }
	 * 
	 * 
	 * @PostMapping("/nominee/add") public ResponseEntity<?>
	 * addNominee(@Valid @RequestBody CustNomineeRequest custNomineeDtlsRequest,
	 * Errors error) { logger.info(":Adding New Nominnee--"); if (error.hasErrors())
	 * return new ResponseEntity<>( error.getAllErrors().stream().map(data ->
	 * data.getDefaultMessage()).collect(Collectors.toList()),
	 * HttpStatus.NOT_ACCEPTABLE); String msg =
	 * this.getServiceManager().getCustomerService().saveOrUpdateCustNomineeDtls(
	 * custNomineeDtlsRequest); Response<String> response =
	 * Response.<String>builder().response(msg).status(HttpStatus.OK.value()).build(
	 * ); return new ResponseEntity<>(response, HttpStatus.ACCEPTED); }
	 */
}
