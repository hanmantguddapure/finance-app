package com.app.finance.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.app.finance.dto.FDAccountDto;
import com.app.finance.dto.FDInterestDto;
import com.app.finance.dto.FDSummaryDto;
import com.app.finance.entity.CustDetail;
import com.app.finance.entity.FDAccount;
import com.app.finance.entity.FDInterest;
import com.app.finance.exception.RecordNotFound;
import com.app.finance.utils.DateUtils;

@Service
public class FDAccountServiceImpl extends DaoServicess implements FDAccountService {

	@Override
	public FDAccountDto saveorUpdateFDAccount(FDAccountDto fdAccountDto) {
		Optional<CustDetail> custDetail = this.getDaoManager().getCustomerDao()
				.findCustomerDtlById(fdAccountDto.getCustId());
		if (!custDetail.isPresent())
			throw new RecordNotFound("customer not found");
		FDAccount fdAccount = new FDAccount();
		fdAccount.setCustId(custDetail.get());
		fdAccount.setAmount(fdAccountDto.getAmount());
		fdAccount.setStartDate(fdAccountDto.getStartDate());
		fdAccount.setEndDate(fdAccountDto.getEndDate());
		fdAccount.setIterest(fdAccountDto.getInterest());
		// fdAccount.setIterestAmt(fdAccountDto.getInterestAmt());
		fdAccount.setIsActive((byte) 1);
		fdAccount = this.getDaoManager().getFdAccountDao().saveOrUpdateFDAccount(fdAccount);
		fdAccountDto.setAccountNo(fdAccount.getAccountNo());
		return fdAccountDto;
	}

	@Override
	public FDAccountDto findByAccountId(Long accountNo) {
		Optional<FDAccount> fdAccount = this.getDaoManager().getFdAccountDao().findByAccountId(accountNo);
		if (!fdAccount.isPresent())
			throw new RecordNotFound("FD Account Not Found");
		List<FDInterest> fdInterests = this.getDaoManager().getFdAccountDao()
				.findPaidInterestByFdAccountNo(fdAccount.get());
		FDAccountDto fdAccountDto = new FDAccountDto();
		fdAccountDto.setAccountNo(fdAccount.get().getAccountNo());
		fdAccountDto.setCustName(fdAccount.get().getCustId().getFullName());
		fdAccountDto.setAmount(fdAccount.get().getAmount());
		fdAccountDto.setInterest(fdAccount.get().getIterest());
		fdAccountDto.setStartDate(fdAccount.get().getStartDate());
		fdAccountDto.setEndDate(fdAccount.get().getEndDate());
		fdAccountDto.setCustId(fdAccount.get().getCustId().getCustId());
		fdAccountDto.setClosingDate(fdAccount.get().getClosingDate());
		fdAccountDto.setRemark(fdAccount.get().getRemark());
		if (fdAccount.get().getIsActive() == 1)
			fdAccountDto.setIsActive("Active");
		else
			fdAccountDto.setIsActive("Closed");

		if (fdInterests != null && fdInterests.size() > 0) {
			fdAccountDto
					.setInterestAmt(fdInterests.stream().mapToDouble(fdInterest -> fdInterest.getPaidInterest()).sum());

			FDInterest latestPaidInterest = fdInterests.get(fdInterests.size() - 1);
			fdAccountDto.setInterstPayFrom(latestPaidInterest.getToDate().plusDays(1));
			List<FDInterestDto> fdInterestDtos = fdInterests.stream().map(fdInterestDtl -> {
				FDInterestDto fdInterestDto = new FDInterestDto();
				fdInterestDto.setFromDate(fdInterestDtl.getFromDate());
				fdInterestDto.setToDate(fdInterestDtl.getToDate());
				fdInterestDto.setInterestAmt(fdInterestDtl.getPaidInterest());
				fdInterestDto.setPaidDate(fdInterestDtl.getPaidDate());
				return fdInterestDto;
			}).collect(Collectors.toList());
			fdAccountDto.setPaidInterestHistory(fdInterestDtos);

		}
		if (fdAccountDto.getInterstPayFrom() == null)
			fdAccountDto.setInterstPayFrom(fdAccountDto.getStartDate());
		if (fdAccountDto.getIsActive().equalsIgnoreCase("Active")) {
			fdAccountDto.setPendingMonthsOfInterest(
					DateUtils.getMontDifference(fdAccountDto.getInterstPayFrom(), LocalDate.now()));
			if (fdAccountDto.getPendingMonthsOfInterest() > 0) {
				fdAccountDto.setPendingInterestAmt(this.calculateInterestAmt(fdAccountDto.getAmount(),
						fdAccountDto.getInterest(), fdAccountDto.getPendingMonthsOfInterest()));
				fdAccountDto.setInterestPayTo(
						fdAccountDto.getInterstPayFrom().plusMonths(fdAccountDto.getPendingMonthsOfInterest()));
			} else
				fdAccountDto.setInterestPayTo(fdAccountDto.getInterstPayFrom()
						.plusDays(DateUtils.getDays(fdAccountDto.getInterstPayFrom(), LocalDate.now())));
		}
		return fdAccountDto;
	}

	@Override
	public List<FDAccountDto> findByIsActive(Byte isActive) {
		List<FDAccount> fdAccounts = null;
		if (isActive == null)
			throw new NullPointerException("isActive not be null");
		if (isActive == 2)
			fdAccounts = (List<FDAccount>) this.getDaoManager().getFdAccountDao().findAllFDAccounts();
		else
			fdAccounts = this.getDaoManager().getFdAccountDao().findByIsActive(isActive);
		if (null != fdAccounts && fdAccounts.size() < 0)
			throw new RecordNotFound("list is empty");
		List<FDAccountDto> accountDtos = fdAccounts.stream().map(fdAccount -> {
			FDAccountDto accountDto = new FDAccountDto();
			accountDto.setAccountNo(fdAccount.getAccountNo());
			accountDto.setCustName(fdAccount.getCustId().getFullName());
			accountDto.setAmount(fdAccount.getAmount());
			accountDto.setInterest(fdAccount.getIterest());
			List<FDInterest> fdInterests = this.getDaoManager().getFdAccountDao()
					.findPaidInterestByFdAccountNo(fdAccount);
			if (fdInterests != null && fdInterests.size() > 0) {
				accountDto.setInterestAmt(
						fdInterests.stream().mapToDouble(fdInterest -> fdInterest.getPaidInterest()).sum());
			}
			accountDto.setStartDate(fdAccount.getStartDate());
			accountDto.setEndDate(fdAccount.getEndDate());
			accountDto.setClosingDate(fdAccount.getClosingDate());
			return accountDto;
		}).collect(Collectors.toList());
		return accountDtos;
	}

	@Override
	public List<FDAccountDto> findByCustId(Long custId) {
		Optional<CustDetail> custDetail = this.getDaoManager().getCustomerDao().findCustomerDtlById(custId);
		if (!custDetail.isPresent())
			throw new RecordNotFound("Customer Not Found");
		List<FDAccount> fdAccounts = this.getDaoManager().getFdAccountDao().findByCustId(custDetail.get());
		if (fdAccounts != null && fdAccounts.size() > 0) {
			List<FDAccountDto> accountDtos = fdAccounts.stream().map(fdaccountdtl -> {
				FDAccountDto accountDto = new FDAccountDto();
				List<FDInterest> fdInterests = this.getDaoManager().getFdAccountDao()
						.findPaidInterestByFdAccountNo(fdaccountdtl);
				if (fdInterests != null && fdInterests.size() > 0) {
					accountDto.setInterestAmt(
							fdInterests.stream().mapToDouble(fdInterest -> fdInterest.getPaidInterest()).sum());
				}

				accountDto.setAccountNo(fdaccountdtl.getAccountNo());
				accountDto.setAmount(fdaccountdtl.getAmount());
				accountDto.setInterest(fdaccountdtl.getIterest());
				accountDto.setStartDate(fdaccountdtl.getStartDate());
				accountDto.setEndDate(fdaccountdtl.getEndDate());
				accountDto.setClosingDate(fdaccountdtl.getClosingDate());
				if (fdaccountdtl.getIsActive() == 1)
					accountDto.setIsActive("Active");
				else
					accountDto.setIsActive("Closed");
				return accountDto;
			}).collect(Collectors.toList());
			return accountDtos;
		}
		return null;
	}

	@Override
	public FDAccountDto closeAccount(FDAccountDto fdAccountDto) {
		Optional<FDAccount> fdAccount = this.getDaoManager().getFdAccountDao()
				.findByAccountId(fdAccountDto.getAccountNo());
		if (!fdAccount.isPresent())
			throw new RecordNotFound("FD Account Not Exist");
		// fdAccount.get().setIterestAmt(fdAccountDto.getInterestAmt());
		// fdAccount.get().setIterest(fdAccountDto.getInterest());
		fdAccount.get().setClosingDate(LocalDate.now());
		fdAccount.get().setRemark(fdAccountDto.getRemark());
		fdAccount.get().setIsActive((byte) 0);
		this.getDaoManager().getFdAccountDao().saveOrUpdateFDAccount(fdAccount.get());
		return fdAccountDto;
	}

	@Override
	public FDInterestDto saveOrUpdateInterest(FDInterestDto fdInterestDto) {
		Optional<FDAccount> fdAccount = this.getDaoManager().getFdAccountDao()
				.findByAccountId(fdInterestDto.getFdAccountId());
		if (!fdAccount.isPresent())
			throw new RecordNotFound("FD Account Not Exist");
		if (fdAccount.get().getIterestAmt() != null)
			fdAccount.get().setIterestAmt(fdInterestDto.getInterestAmt() + fdAccount.get().getIterestAmt());
		else
			fdAccount.get().setIterestAmt(fdInterestDto.getInterestAmt());
		FDInterest fdInterest = new FDInterest();
		fdInterest.setFdAccountId(fdAccount.get());
		fdInterest.setPaidInterest(fdInterestDto.getInterestAmt());
		fdInterest.setFromDate(fdInterestDto.getFromDate());
		fdInterest.setToDate(fdInterestDto.getToDate());
		fdInterest.setPaidDate(LocalDate.now());
		fdInterest.setPaidMode(fdInterestDto.getPaidMode());
		this.getDaoManager().getFdAccountDao().saveOrUpdateFDAccount(fdAccount.get());
		fdInterest = this.getDaoManager().getFdAccountDao().saveOrUpdateInterest(fdInterest);
		fdInterestDto.setInterestId(fdInterest.getInterestId());
		fdInterestDto.setPaidDate(fdInterest.getPaidDate());
		return fdInterestDto;
	}

	private Double calculateInterestAmt(double amount, float interest, int months) {
		Double montlyInterest = (amount / 100) * interest;
		return months * montlyInterest;
	}

	@Override
	public List<CustDetail> findAllFDHolders() {
		List<FDAccount> fdAccounts = (List<FDAccount>) this.getDaoManager().getFdAccountDao().findAllFDAccounts();
		return fdAccounts.stream().map(fdAccount -> fdAccount.getCustId()).collect(Collectors.toList());
	}

	@Override
	public FDSummaryDto getFDSummaryByStatus(String status) {
		List<FDAccount> fdAccounts = null;
		if (status.equalsIgnoreCase("Active"))
			fdAccounts = this.getDaoManager().getFdAccountDao().findByIsActive((byte) 1);
		if (fdAccounts != null && fdAccounts.size() > 0) {
			List<FDInterest> fdInterests = this.getDaoManager().getFdAccountDao()
					.findPaidInterestByFdAccountNos(fdAccounts);
			FDSummaryDto fdSummaryDto = new FDSummaryDto();
			fdSummaryDto.setNofFd(fdAccounts.stream().collect(Collectors.counting()));
			fdSummaryDto.setFdAmount(fdAccounts.stream().mapToDouble(fdAccount -> fdAccount.getAmount()).sum());
			fdSummaryDto.setPaidInterest(
					fdInterests.stream().mapToDouble(fdInterest -> fdInterest.getPaidInterest()).sum());
			fdAccounts.stream().forEach(fdAccount -> {
				List<FDInterest> pendigFdIntrests = this.getDaoManager().getFdAccountDao()
						.findPaidInterestByFdAccountNo(fdAccount);
				LocalDate lastPaidDate=null;
				if (pendigFdIntrests != null && pendigFdIntrests.size() > 0) {
					FDInterest latestPaidInterest = fdInterests.get(fdInterests.size() - 1);
					lastPaidDate = latestPaidInterest.getToDate().plusDays(1);
				}
					if (lastPaidDate == null)
						lastPaidDate = fdAccount.getStartDate();
					int pendingMonths = DateUtils.getMontDifference(lastPaidDate, LocalDate.now());
					if (pendingMonths > 0) {
						Double intrestAmt = this.calculateInterestAmt(fdAccount.getAmount(), fdAccount.getIterest(),
								pendingMonths);
						if (fdSummaryDto.getPendingIntrest() == null)
							fdSummaryDto.setPendingIntrest((double) 0);
						fdSummaryDto.setPendingIntrest(fdSummaryDto.getPendingIntrest() + intrestAmt);
					}
			});

			return fdSummaryDto;
		}
		return null;

	}
}