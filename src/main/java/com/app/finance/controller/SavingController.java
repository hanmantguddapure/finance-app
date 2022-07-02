package com.app.finance.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.finance.model.request.SavingAccounReq;
import com.app.finance.model.request.SavingAccountDepositeReq;
import com.app.finance.model.response.Response;
import com.app.finance.model.response.SavingAccounResponse;
import com.app.finance.model.response.SavingAccountDepositeResponse;

@RestController
@RequestMapping(value = "/saving")
public class SavingController extends ControllerManager {

	@PostMapping("/create")
	ResponseEntity<?> createNewAccount(@RequestBody SavingAccounReq savingAccounReq) {
		SavingAccounResponse msg = this.getServiceManager().getSavingAccountService().saveOrUpdate(savingAccounReq);
		Response<SavingAccounResponse> response = Response.<SavingAccounResponse>builder().response(msg)
				.status(HttpStatus.OK.value()).build();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/find/{accountId}")
	ResponseEntity<?> findByAccountId(@PathVariable(name = "accountId") Long accountId) {
		SavingAccounResponse msg = this.getServiceManager().getSavingAccountService().findByAccountNumber(accountId);
		Response<SavingAccounResponse> response = Response.<SavingAccounResponse>builder().response(msg)
				.status(HttpStatus.OK.value()).build();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/find-by-account-status/{isAcitve}")
	ResponseEntity<?> findByAccountStatus(@PathVariable(name = "isActive") Short isActive) {
		List<SavingAccounResponse> responselst = this.getServiceManager().getSavingAccountService()
				.findAllByStatus(isActive);
		Response<List<SavingAccounResponse>> response = Response.<List<SavingAccounResponse>>builder()
				.response(responselst).status(HttpStatus.OK.value()).build();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/deposite/add-withdraw")
	ResponseEntity<?> addDeposite(@RequestBody SavingAccountDepositeReq savingAccountDepositeReq) {
		SavingAccountDepositeResponse msg = this.getServiceManager().getSavingAccountService()
				.saveOrUpdate(savingAccountDepositeReq);
		Response<SavingAccountDepositeResponse> response = Response.<SavingAccountDepositeResponse>builder()
				.response(msg).status(HttpStatus.OK.value()).build();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
