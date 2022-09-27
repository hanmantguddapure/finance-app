package com.app.finance.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.app.finance.entity.ExpenseDetail;
import com.app.finance.entity.ExpenseTypes;

public interface ExpenseDetailRepo extends CrudRepository<ExpenseDetail, Long> {
	public List<ExpenseDetail> findExpensesByFromDate(LocalDate fromDate);

	public List<ExpenseDetail> findByFromDateBetween(LocalDate fromDate, LocalDate toDate);

	@Query(" from ExpenseDetail where fromDate>=:fromDate and toDate>=:toDate")
	List<ExpenseDetail> getExpenseDetailByDate(@Param("fromDate") LocalDate fromDate,
			@Param("toDate") LocalDate toDate);

	@Query(" from ExpenseDetail where expenseType.expenseType=:expenseType")
	List<ExpenseDetail> getExpenseDetailByExpenseType(@Param("expenseType") String expenseType);

	List<ExpenseDetail> findByExpenseTypeAndFromDateBetween(ExpenseTypes expenseTypeId, LocalDate fromDate, LocalDate toDate);

	public List<ExpenseDetail> findByExpenseType(ExpenseTypes expenseTypeId);

}
