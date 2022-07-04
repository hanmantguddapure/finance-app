package com.app.finance.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.app.finance.entity.CustDetail;
import com.app.finance.entity.SavingAccDepositeDtls;
import com.app.finance.entity.SavingAccount;
import com.app.finance.exception.BadRequest;
import com.app.finance.exception.RecordNotFound;
import com.app.finance.model.request.SavingAccounReq;
import com.app.finance.model.request.SavingAccountDepositeReq;
import com.app.finance.model.response.SavingAccounResponse;
import com.app.finance.model.response.SavingAccountDepositeResponse;

@Service
public class SavingAccountServiceImpl extends DaoServicess implements SavingAccountService {

	@Override
	public SavingAccounResponse saveOrUpdate(SavingAccounReq savingAccounReq) {
		Optional<CustDetail> custPersionalDetail = this.getDaoManager().getCustomerDao()
				.findCustomerDtlById(Long.valueOf(savingAccounReq.getCustId()));
		if (!custPersionalDetail.isPresent())
			throw new RecordNotFound("Customer Not Found");
		SavingAccount savingAccountEntity = SavingAccount.builder().amount(savingAccounReq.getAmount())
				.custId(custPersionalDetail.get()).interest(savingAccounReq.getInterest()).isActive((short) 1)
				.startDate(LocalDate.now()).build();
		savingAccountEntity = this.getDaoManager().getSavingAccDao().saveOrUpdate(savingAccountEntity);
		SavingAccDepositeDtls savingAccounDepositeDtls = SavingAccDepositeDtls.builder()
				.amount(savingAccounReq.getAmount()).accountNumber(savingAccountEntity).date(LocalDate.now())
				.isCredit((short) 1).build();
		savingAccounDepositeDtls = this.getDaoManager().getSavingAccDao().saveOrUpdate(savingAccounDepositeDtls);
		return SavingAccounResponse.builder().accountNumber(savingAccountEntity.getAccountNumber()).build();
	}

	@Override
	public SavingAccounResponse findByAccountNumber(Long accountNumber) {
		Optional<SavingAccount> savingAccountDtlOptional = this.getDaoManager().getSavingAccDao()
				.findById(accountNumber);
		if (!savingAccountDtlOptional.isPresent())
			throw new RecordNotFound("Saving account Not Found");
		SavingAccount savingAccountEntity = savingAccountDtlOptional.get();
		List<SavingAccDepositeDtls> savingDepositeDtlsLst = this.getDaoManager().getSavingAccDao()
				.findByAccountNumber(savingAccountEntity);
		List<SavingAccountDepositeResponse> depositeResponses = savingDepositeDtlsLst.stream().map(data -> {
			return SavingAccountDepositeResponse.builder().amount(data.getAmount()).date(data.getDate())
					.depositeId(data.getDepositeId()).isCredit(data.getIsCredit()).build();
		}).collect(Collectors.toList());

		return SavingAccounResponse.builder().accountNumber(accountNumber)
				.customerName(savingAccountEntity.getCustId().getFullName()).amount(savingAccountEntity.getAmount())
				.interest(savingAccountEntity.getInterest()).interestAmt(savingAccountEntity.getIntrestAmount())
				.isActive(savingAccountEntity.getIsActive() == 1 ? "Active" : "Closed").deposite(depositeResponses)
				.build();
	}

	@Override
	public List<SavingAccounResponse> findAllByStatus(Short status) {
		List<SavingAccount> savingAccountEntityList = this.getDaoManager().getSavingAccDao().findByIsActive(status);
		List<SavingAccounResponse> accounResponses = savingAccountEntityList.stream().map(data -> {
			return SavingAccounResponse.builder().accountNumber(data.getAccountNumber())
					.customerName(data.getCustId().getFullName()).amount(data.getAmount())
					.startDate(data.getStartDate()).build();
		}).collect(Collectors.toList());

		return accounResponses;
	}

	public SavingAccountDepositeResponse saveOrUpdate(SavingAccountDepositeReq savingAccounReq) {
		Optional<SavingAccount> savingAccountDtlOptional = this.getDaoManager().getSavingAccDao()
				.findById(savingAccounReq.getAccountNumber());
		if (!savingAccountDtlOptional.isPresent())
			throw new RecordNotFound("Saving account Not Found");
		SavingAccount savingAccountEntity = savingAccountDtlOptional.get();
		if (savingAccounReq.getIsCredit()==0) {
			if (savingAccounReq.getAmount() >= savingAccountEntity.getAmount())
				throw new BadRequest("insufficient balance");
			savingAccountEntity.setAmount(savingAccountEntity.getAmount() - savingAccounReq.getAmount());
		} else
			savingAccountEntity.setAmount(savingAccountEntity.getAmount() + savingAccounReq.getAmount());
		savingAccountEntity = this.getDaoManager().getSavingAccDao().saveOrUpdate(savingAccountEntity);
		SavingAccDepositeDtls savingAccounDepositeDtls = SavingAccDepositeDtls.builder()
				.amount(savingAccounReq.getAmount()).accountNumber(savingAccountEntity).date(LocalDate.now())
				.isCredit(savingAccounReq.getIsCredit()).build();
		savingAccounDepositeDtls = this.getDaoManager().getSavingAccDao().saveOrUpdate(savingAccounDepositeDtls);
		return SavingAccountDepositeResponse.builder().depositeId(savingAccounDepositeDtls.getDepositeId()).build();
	}

}
