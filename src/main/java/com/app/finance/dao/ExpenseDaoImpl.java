package com.app.finance.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.finance.entity.ExpenseDetail;
import com.app.finance.entity.ExpenseTypes;
import com.app.finance.repositories.ExpenseDetailRepo;
import com.app.finance.repositories.ExpenseTypesRepo;

@Repository
public class ExpenseDaoImpl implements ExpenseDao {

	@Autowired
	ExpenseDetailRepo expenseDetailRepo;
	@Autowired
	ExpenseTypesRepo expenseTypeRepo;

	@Override
	public ExpenseDetail addExpense(ExpenseDetail expenseDetail) {
		return expenseDetailRepo.save(expenseDetail);
	}

	@Override
	public ExpenseTypes addExpenseTypes(ExpenseTypes expenseTypes) {
		return expenseTypeRepo.save(expenseTypes);
	}

	@Override
	public List<ExpenseTypes> getAllExpenseTypes() {
		return (List<ExpenseTypes>) expenseTypeRepo.findAll();
	}

	@Override
	public List<ExpenseDetail> getExpenseDetailByDate(LocalDate fromDate, LocalDate toDate) {
		return expenseDetailRepo.getExpenseDetailByDate(fromDate, toDate);
	}

	public Optional<ExpenseTypes> getExpenseTypeById(Long expenseTypeId) {
		return expenseTypeRepo.findById(expenseTypeId);
	}

	@Override
	public List<ExpenseDetail> findExpenseByFromDate(LocalDate fromDate) {
		return expenseDetailRepo.findExpensesByFromDate(fromDate);
	}

	@Override
	public List<ExpenseDetail> findByFromDateBetween(LocalDate fromDate, LocalDate toDate) {
		return expenseDetailRepo.findByFromDateBetween(fromDate, toDate);
	}

	@Override
	public List<ExpenseDetail> getExpenseDetailBExpenseType(String expenseType) {
		return expenseDetailRepo.getExpenseDetailByExpenseType(expenseType);
	}

	// @Override
	public void deleteExpenseDtl(Long expenseDtlId) {
		expenseDetailRepo.deleteById(expenseDtlId);
	}
}
