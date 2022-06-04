package com.app.finance.controller;

import java.util.stream.Collectors;

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

import com.app.finance.dto.ExpenseDto;
import com.app.finance.entity.ExpenseTypes;
import com.app.finance.utils.DateUtils;

@RestController
@RequestMapping(value = "/expense")
public class ExpenseController extends ControllerManager {
	private final Logger logger = LogManager.getLogger(ExpenseController.class);

	@PostMapping(value = "/add-details")
	public ResponseEntity<?> addExpenseDetails(@Valid @RequestBody ExpenseDto expenseDetailDto, Errors errors) {
		if (errors.hasErrors())
			return new ResponseEntity<>(
					errors.getAllErrors().stream().map(data -> data.getDefaultMessage()).collect(Collectors.toList()),
					HttpStatus.BAD_REQUEST);
		return ResponseEntity.ok(this.getServiceManager().getExpenseService().addExpenses(expenseDetailDto));

	}

	@PostMapping(value = "/add-expense-type")
	public ResponseEntity<?> addExpenseTypes(@RequestBody ExpenseTypes expenseTypes) {
		return ResponseEntity.ok(this.getServiceManager().getExpenseService().addExpenseTypes(expenseTypes));
	}

	@GetMapping(value = "/get-expense-types")
	public ResponseEntity<?> getExpenseTypes() {
		return ResponseEntity.ok(this.getServiceManager().getExpenseService().getAllExpenseTypes());
	}

	@PostMapping(value = "/get-expense-detail")
	public ResponseEntity<?> getExpenseDetailByDate(@RequestBody ExpenseDto expenseDto) {
		return ResponseEntity.ok(this.getServiceManager().getExpenseService().getExpenseDetailByDate(expenseDto));
	}

	@GetMapping(value = "/get-expenses/{fromDate}")
	public ResponseEntity<?> getExpensesByFromDate(@PathVariable("fromDate") String fromDate) {

		return ResponseEntity.ok(this.getServiceManager().getExpenseService()
				.findExpensesByFromDate(DateUtils.convertStringToLocalDate(fromDate)));
	}

	@GetMapping(value = "/expense-type/{expenseType}")
	public ResponseEntity<?> getExpensesByExpenseType(@PathVariable("expenseType") String expenseType) {

		return ResponseEntity.ok(this.getServiceManager().getExpenseService().findExpensesByExpenseType(expenseType));
	}

	@GetMapping(value = "/get-expense-between/{fromDate}/{toDate}")
	public ResponseEntity<?> getExpensesByBewteenDate(@PathVariable String fromDate, @PathVariable String toDate) {
		return ResponseEntity.ok(this.getServiceManager().getExpenseService().findByFromDateBetween(
				DateUtils.convertStringToLocalDate(fromDate), DateUtils.convertStringToLocalDate(toDate)));
	}

}
