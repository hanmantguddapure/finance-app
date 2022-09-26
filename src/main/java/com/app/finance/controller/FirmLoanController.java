package com.app.finance.controller;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.finance.dto.FirmLoanDto;
import com.app.finance.dto.ShortTermLoanDto;

@RestController
@RequestMapping("/Firm")
public class FirmLoanController extends ControllerManager {
	private static final Logger logger = LogManager.getLogger(FirmLoanController.class);

	@PostMapping("/new")
	public ResponseEntity<?> createNewFirmLoan(@Valid @RequestBody FirmLoanDto firmLoanDto, Errors errors) {
		logger.info(":creating new firm loan--");
		if (errors.hasErrors())
			return ResponseEntity.ok(
					errors.getAllErrors().stream().map(data -> data.getDefaultMessage()).collect(Collectors.toList()));
		return ResponseEntity.ok(this.getServiceManager().getFirmLoanService().saveOrUpdate(firmLoanDto));
	}

	@PostMapping("/close")
	public ResponseEntity<?> closeFD(@Valid @RequestBody FirmLoanDto firmLoanDto, Errors errors) {
		logger.info(":closing firm Loan Of--", firmLoanDto.getLoanFrom());
		if (errors.hasErrors())
			return ResponseEntity.ok(
					errors.getAllErrors().stream().map(data -> data.getDefaultMessage()).collect(Collectors.toList()));
		return ResponseEntity.ok(this.getServiceManager().getFirmLoanService().saveOrUpdate(firmLoanDto));
	}

	@RequestMapping(value = "/find/{firmLoanId}")
	public ResponseEntity<?> getByFirmLoanId(@PathVariable Long firmLoanId) throws IOException {
		logger.info(":find firm loan Detail Of--", firmLoanId);
		return ResponseEntity.ok(this.getServiceManager().getFirmLoanService().findFirmLoanDtlById(firmLoanId));

	}

	@RequestMapping(value = "/find-all/{status}")
	public ResponseEntity<?> getFdsBySatus(@PathVariable Byte status) throws IOException {
		logger.info(":find all firm loan status of--", status);
		return ResponseEntity.ok(this.getServiceManager().getFirmLoanService().findAllByStatus(status));

	}

	@PostMapping("/short-term-loan/new")
	public ResponseEntity<?> createNewShortTermLoan(@Valid @RequestBody ShortTermLoanDto shortTermLoanDto,
			Errors errors) {
		logger.info(":creating/updating new short term loan--");
		if (errors.hasErrors())
			return ResponseEntity.ok(
					errors.getAllErrors().stream().map(data -> data.getDefaultMessage()).collect(Collectors.toList()));
		return ResponseEntity.ok(this.getServiceManager().getFirmLoanService().saveOrUpdate(shortTermLoanDto));
	}

	@PostMapping("/short-term-loan/close")
	public ResponseEntity<?> closeShortFirmLoan(@Valid @RequestBody ShortTermLoanDto shortTermLoanDto, Errors errors) {
		logger.info(":closing short term Loan Of--", shortTermLoanDto.getCustFullName());
		if (errors.hasErrors())
			return ResponseEntity.ok(
					errors.getAllErrors().stream().map(data -> data.getDefaultMessage()).collect(Collectors.toList()));
		return ResponseEntity.ok(this.getServiceManager().getFirmLoanService().saveOrUpdate(shortTermLoanDto));
	}

	@RequestMapping(value = "/short-term-loan/find/{shortTermLoanId}")
	public ResponseEntity<?> getShortTermLoanByLoanId(@PathVariable Long shortTermLoanId) throws IOException {
		logger.info(":find short term loan Detail Of--", shortTermLoanId);
		return ResponseEntity
				.ok(this.getServiceManager().getFirmLoanService().findShortTermLoanDtlById(shortTermLoanId));

	}

	@GetMapping(value = "/short-term-loan/find-all/{status}")
	public ResponseEntity<?> getShortTermLoansBySatus(@PathVariable Byte status) throws IOException {
		logger.info(":find all firm loan status of--", status);
		return ResponseEntity.ok(this.getServiceManager().getFirmLoanService().findAllShortTermLoanByStatus(status));

	}

}
