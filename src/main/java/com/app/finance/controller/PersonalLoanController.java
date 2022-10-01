package com.app.finance.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.finance.model.request.PersonalLoanDtlsReq;
import com.app.finance.model.request.PersonalLoanInstallmentDtlReq;

@RestController
@RequestMapping("/personal")
public class PersonalLoanController extends ControllerManager {

	@PostMapping("/new")
	public ResponseEntity<?> create(@RequestBody PersonalLoanDtlsReq personalLoanDtlsReq) {
		return ResponseEntity.ok(this.getServiceManager().getPersonalLoanService().saveOrUpdate(personalLoanDtlsReq));
	}

	@PostMapping("/installment/add")
	public ResponseEntity<?> create(@Valid @RequestBody PersonalLoanInstallmentDtlReq personalLoanDtlsReq) {
		return ResponseEntity.ok(this.getServiceManager().getPersonalLoanService().saveOrUpdate(personalLoanDtlsReq));
	}
	
	@GetMapping("/{loanId}")
	public ResponseEntity<?> create(@PathVariable Long loanId) {
		return ResponseEntity.ok(this.getServiceManager().getPersonalLoanService().findPersonalLoanAccountDtls(loanId));
	}
}
