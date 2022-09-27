package com.app.finance.service;

import java.time.LocalDate;
import java.util.List;

import com.app.finance.dto.ExpenseDto;
import com.app.finance.entity.ExpenseDetail;
import com.app.finance.entity.ExpenseTypes;

public interface ExpenseService {
	public ExpenseDto addExpenses(ExpenseDto expenseDetailDto);

	public List<ExpenseDetail> getExpenseDetailByDate(ExpenseDto expenseDto);

	public List<ExpenseTypes> getAllExpenseTypes();

	public ExpenseTypes addExpenseTypes(ExpenseTypes expenseTypes);

	public List<ExpenseDto> findExpensesByFromDate(LocalDate fromDate);

	public List<ExpenseDto> findByFromDateBetween(Long expenseTypeId,LocalDate fromDate, LocalDate toDate);

	public List<ExpenseDto> findExpensesByExpenseType(Long expenseTypeId);
	
	/** This method update the expense type name.
	 * 
	 * @param expenseTypeId
	 * @param newExpenseTypeName
	 * @return {@link String}
	 */
	public String updateExpenseTypeName(Long expenseTypeId,String newExpenseTypeName);
	
	public String deleteExpenseDtl(Long expenseTypeId);
}
