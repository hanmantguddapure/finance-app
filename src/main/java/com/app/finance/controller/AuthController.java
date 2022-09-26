package com.app.finance.controller;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.finance.dto.AuthDetailsDto;

@RestController
@RequestMapping("/authontication")
public class AuthController extends ControllerManager {
	private final Logger logger = LogManager.getLogger(AuthController.class);
	
	@GetMapping("/check-username/{userName}")
	public ResponseEntity<?> checkUserName(@PathVariable String userName)  {
		logger.info(":Checking userName =>" + userName);
		if (userName==null||userName.isEmpty()) {
			throw new NullPointerException("please enter username");
			}
			return ResponseEntity.ok(this.getServiceManager().getAuthService().authUserName(userName));
	}

	@PostMapping("/check-password")
	public ResponseEntity<?> checkPassword(@RequestBody @Valid AuthDetailsDto authDetails, Errors error,HttpServletRequest httpServletRequest) {
		logger.info(":Checking password =>" + authDetails.getUserName() + " " + authDetails.getPassword());
		if (error.hasErrors())
			return new ResponseEntity<>(
					error.getAllErrors().stream().map(data -> data.getDefaultMessage()).collect(Collectors.toList()),HttpStatus.BAD_REQUEST);
			authDetails.setLoggedInUserIpAddress(httpServletRequest.getRemoteAddr());
			return ResponseEntity.ok(this.getServiceManager().getAuthService().authPassaword(authDetails));
	}
}
