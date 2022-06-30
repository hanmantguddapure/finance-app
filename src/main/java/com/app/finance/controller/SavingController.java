package com.app.finance.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.finance.model.request.SavingAccounReq;
import com.app.finance.model.response.Response;
import com.app.finance.model.response.SavingAccounResponse;


@RestController
@RequestMapping(value ="/saving")
public class SavingController extends ControllerManager{

	@PostMapping("/create")
	ResponseEntity<?> createNewAccount(@RequestBody SavingAccounReq savingAccounReq) {
		SavingAccounResponse msg = this.getServiceManager().getSavingAccountService().saveOrUpdate(savingAccounReq);
		Response<SavingAccounResponse> response = Response.<SavingAccounResponse>builder().response(msg)
				.status(HttpStatus.OK.value()).build();
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	@GetMapping("/test")
	public String test() {
		return "hello";
	}
}
