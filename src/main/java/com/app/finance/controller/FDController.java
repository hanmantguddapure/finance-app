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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.finance.dto.FDAccountDto;
import com.app.finance.dto.FDInterestDto;

@RestController
@RequestMapping("/FD")
public class FDController extends ControllerManager {
	private static final Logger logger = LogManager.getLogger(FDController.class);

	@PostMapping("/new")
	public ResponseEntity<?> createNewFD(@Valid @RequestBody FDAccountDto fdAccountDto, Errors errors) {
		logger.info(":creating new fd--");
		if (errors.hasErrors())
			return ResponseEntity.ok(
					errors.getAllErrors().stream().map(data -> data.getDefaultMessage()).collect(Collectors.toList()));
		return ResponseEntity.ok(this.getServiceManager().getFdAccountService().saveorUpdateFDAccount(fdAccountDto));
	}

	@PostMapping("/close")
	public ResponseEntity<?> closeFD(@Valid @RequestBody FDAccountDto fdAccountDto, Errors errors) {
		logger.info(":closing fd Of--", fdAccountDto.getCustName());
		if (errors.hasErrors())
			return ResponseEntity.ok(
					errors.getAllErrors().stream().map(data -> data.getDefaultMessage()).collect(Collectors.toList()));
		return ResponseEntity.ok(this.getServiceManager().getFdAccountService().closeAccount(fdAccountDto));
	}

	@GetMapping("/find/{accountNo}")
	public ResponseEntity<?> findByAccountNo(@PathVariable Long accountNo) {
		logger.info(":finding fd Of--", accountNo);
		if (accountNo == null)
			throw new NullPointerException("Account No. may not be null");
		return ResponseEntity.ok(this.getServiceManager().getFdAccountService().findByAccountId(accountNo));
	}

	@RequestMapping(value = "/find-all/{fdStatus}")
	public ResponseEntity<?> getFdsBySatus(@PathVariable Byte fdStatus) throws IOException {
		logger.info(":find all fd statuds of--", fdStatus);
		return ResponseEntity.ok(this.getServiceManager().getFdAccountService().findByIsActive(fdStatus, null, null));

	}

	@RequestMapping(value = "/find-all/{fdStatus}/{fromDate}/{toDate}")
	public ResponseEntity<?> getFdsBySatus(@PathVariable Byte fdStatus, @PathVariable String fromDate,
			@PathVariable String toDate) throws IOException {
		logger.info(":find all fd statuds of--", fdStatus);
		return ResponseEntity
				.ok(this.getServiceManager().getFdAccountService().findByIsActive(fdStatus, fromDate, toDate));

	}

	@GetMapping("/find-all-holders")
	public ResponseEntity<?> findAll() {
		logger.info(":Find All FD Holdres--");
		return new ResponseEntity<>(this.getServiceManager().getFdAccountService().findAllFDHolders(),
				HttpStatus.ACCEPTED);
	}

	@GetMapping("/findAll/{custId}")
	public ResponseEntity<?> getCustomerFDs(@PathVariable Long custId) {
		logger.info(":find all fd of customer--", custId);
		return ResponseEntity.ok(this.getServiceManager().getFdAccountService().findByCustId(custId));
	}

	@RequestMapping(value = "/download/{accountNo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<?> downloadCustPDF(@PathVariable Long accountNo) throws IOException {
		logger.info(":download fd Of--", accountNo);
		if (accountNo == null)
			throw new NullPointerException("Account No. may not be null");
		return this.getServiceManager().getDownloadService().downloandCustomerFD(accountNo);

	}

	@PostMapping("/pay-interst-amount")
	public ResponseEntity<?> payFDInterestAmt(@Valid @RequestBody FDInterestDto fdInterestDto, Errors errors) {
		logger.info(":paying interest of FD--", fdInterestDto.getFdAccountId());
		if (errors.hasErrors())
			return ResponseEntity.ok(
					errors.getAllErrors().stream().map(data -> data.getDefaultMessage()).collect(Collectors.toList()));
		return ResponseEntity.ok(this.getServiceManager().getFdAccountService().saveOrUpdateInterest(fdInterestDto));
	}

}
