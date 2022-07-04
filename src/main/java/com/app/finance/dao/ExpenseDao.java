package com.app.finance.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.app.finance.entity.ExpenseDetail;
import com.app.finance.entity.ExpenseTypes;

public interface ExpenseDao {
	public ExpenseTypes addExpenseTypes(ExpenseTypes expenseTypes);

	public List<ExpenseTypes> getAllExpenseTypes();

	public ExpenseDetail addExpense(ExpenseDetail expenseDetail);

	public List<ExpenseDetail> findExpenseByFromDate(LocalDate fromDate);

	public List<ExpenseDetail> findByFromDateBetween(LocalDate fromDate, LocalDate toDate);

	public List<ExpenseDetail> getExpenseDetailByDate(LocalDate fromDate, LocalDate toDate);

	public Optional<ExpenseTypes> getExpenseTypeById(Long expenseTypeId);

	public List<ExpenseDetail> getExpenseDetailBExpenseType(String expenseType);

	public void deleteExpenseDtl(Long expenseDtlId);
}
