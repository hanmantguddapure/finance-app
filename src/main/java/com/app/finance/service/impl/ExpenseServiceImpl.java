package com.app.finance.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.app.finance.constant.BaseConstant;
import com.app.finance.dto.ExpenseDto;
import com.app.finance.entity.ExpenseDetail;
import com.app.finance.entity.ExpenseTypes;
import com.app.finance.exception.RecordNotFound;
import com.app.finance.service.DaoServicess;
import com.app.finance.service.ExpenseService;

@Service
public class ExpenseServiceImpl extends DaoServicess implements ExpenseService {

	@Override
	public ExpenseDto addExpenses(ExpenseDto expenseDetailDto) {
		Optional<ExpenseTypes> expenseTypes = this.getDaoManager().getExpenseDao()
				.getExpenseTypeById(expenseDetailDto.getExpenseTypeId());
		if (!expenseTypes.isPresent())
			throw new RecordNotFound("Expense Type Not Exist");
		ExpenseDetail expenseDetail = new ExpenseDetail();
		expenseDetail.setExpenseType(expenseTypes.get());
		expenseDetail.setFromDate(expenseDetailDto.getFromDate());
		expenseDetail.setToDate(expenseDetailDto.getToDate());
		expenseDetail.setRemark(expenseDetailDto.getRemark());
		expenseDetail.setAmount(expenseDetailDto.getAmount());
		expenseDetail = this.getDaoManager().getExpenseDao().addExpense(expenseDetail);
		expenseDetailDto.setExpenseId(expenseDetail.getExpenseId());
		return expenseDetailDto;
	}

	@Override
	public List<ExpenseDetail> getExpenseDetailByDate(ExpenseDto expenseDto) {
		return this.getDaoManager().getExpenseDao().getExpenseDetailByDate(expenseDto.getFromDate(),
				expenseDto.getToDate());
	}

	@Override
	public List<ExpenseTypes> getAllExpenseTypes() {
		return this.getDaoManager().getExpenseDao().getAllExpenseTypes();
	}

	@Override
	public ExpenseTypes addExpenseTypes(ExpenseTypes reqExpenseTypes) {
		if (reqExpenseTypes.getExpenseType() == null && reqExpenseTypes.getExpenseType().length() < 0)
			throw new NullPointerException("expense type may not be null/empty");
		ExpenseTypes expenseTypes = new ExpenseTypes();
		expenseTypes.setExpenseType(reqExpenseTypes.getExpenseType());
		expenseTypes.setLastUpodatedDate(LocalDate.now());
		expenseTypes = this.getDaoManager().getExpenseDao().addExpenseTypes(expenseTypes);
		return expenseTypes;
	}

	@Override
	public List<ExpenseDto> findExpensesByFromDate(LocalDate fromDate) {
		if (fromDate == null)
			throw new NullPointerException("fromDate may not be null");
		List<ExpenseDetail> expenseDetails = this.getDaoManager().getExpenseDao().findExpenseByFromDate(fromDate);
		if (expenseDetails != null && expenseDetails.size() > 0) {
			List<ExpenseDto> expenseDtos = expenseDetails.stream().map(expenseDtl -> {
				ExpenseDto expenseDto = new ExpenseDto();
				expenseDto.setAmount(expenseDtl.getAmount());
				expenseDto.setRemark(expenseDtl.getRemark());
				expenseDto.setExpenseType(expenseDtl.getExpenseType().getExpenseType());
				expenseDto.setFromDate(expenseDtl.getFromDate());
				expenseDto.setToDate(expenseDtl.getToDate());
				return expenseDto;
			}).collect(Collectors.toList());

			return expenseDtos;
		}
		return null;
	}

	@Override
	public List<ExpenseDto> findByFromDateBetween(LocalDate fromDate, LocalDate toDate) {
		if (fromDate == null)
			throw new NullPointerException("fromDate/toDate may not be null");
		List<ExpenseDetail> expenseDetails = this.getDaoManager().getExpenseDao().findByFromDateBetween(fromDate,
				toDate);
		if (expenseDetails != null && expenseDetails.size() > 0) {
			List<ExpenseDto> expenseDtos = expenseDetails.stream().map(expenseDtl -> {
				ExpenseDto expenseDto = new ExpenseDto();
				expenseDto.setAmount(expenseDtl.getAmount());
				expenseDto.setExpenseType(expenseDtl.getExpenseType().getExpenseType());
				expenseDto.setFromDate(expenseDtl.getFromDate());
				expenseDto.setRemark(expenseDtl.getRemark());
				return expenseDto;
			}).collect(Collectors.toList());

			return expenseDtos;
		}
		return null;

	}

	@Override
	public List<ExpenseDto> findExpensesByExpenseType(String expenseType) {
		if (StringUtils.isEmpty(expenseType))
			throw new NullPointerException("fromDate may not be null");
		List<ExpenseDetail> expenseDetails = this.getDaoManager().getExpenseDao()
				.getExpenseDetailBExpenseType(expenseType);
		if (expenseDetails != null && expenseDetails.size() > 0) {
			List<ExpenseDto> expenseDtos = expenseDetails.stream().map(expenseDtl -> {
				ExpenseDto expenseDto = new ExpenseDto();
				expenseDto.setAmount(expenseDtl.getAmount());
				expenseDto.setRemark(expenseDtl.getRemark());
				expenseDto.setExpenseType(expenseDtl.getExpenseType().getExpenseType());
				expenseDto.setFromDate(expenseDtl.getFromDate());
				expenseDto.setToDate(expenseDtl.getToDate());
				return expenseDto;
			}).collect(Collectors.toList());

			return expenseDtos;
		}
		return null;
	}
	
	public String updateExpenseTypeName(Long expenseTypeId, String newExpenseTypeName) {
		Optional<ExpenseTypes> expenseTypesOptional = this.getDaoManager().getExpenseDao()
				.getExpenseTypeById(expenseTypeId);
		if (!expenseTypesOptional.isPresent())
			throw new RecordNotFound(RecordNotFound.Errors.RECORD_NOT_FOUND.name());
		this.getDaoManager().getExpenseDao()
				.addExpenseTypes(ExpenseTypes.builder().expenseTypeId(expenseTypesOptional.get().getExpenseTypeId())
						.expenseType(newExpenseTypeName).lastUpodatedDate(LocalDate.now()).build());
		return BaseConstant.UPDATE_SUCESS_MSG;
	}
}
