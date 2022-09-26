package com.app.finance.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashBoardController extends ControllerManager {
	private static final Logger logger = LogManager.getLogger(DashBoardController.class);

	@GetMapping("/dashboard")
	public ResponseEntity<?> getDashBoardData() {
		logger.info(": fetching dashboard data:--");
		return new ResponseEntity<>(this.getServiceManager().getDashBoardService().getDashBoardData(), HttpStatus.OK);

	}
	
	@GetMapping("/get-all-summary-repo/{loanStatus}/{fdStatus}")
	public ResponseEntity<?> getAllSummaryRepo(@PathVariable String loanStatus,@PathVariable String fdStatus) {
		logger.info(": fetching dashboard data:--");
		return new ResponseEntity<>(this.getServiceManager().getDashBoardService().getAllSummaryReport(loanStatus,fdStatus), HttpStatus.OK);

	}

}
