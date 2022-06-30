package com.app.finance.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.app.finance.entity.CustDetail;
import com.app.finance.entity.SavingAccDepositeDtls;
import com.app.finance.entity.SavingAccount;
import com.app.finance.exception.RecordNotFound;
import com.app.finance.model.request.SavingAccounReq;
import com.app.finance.model.response.SavingAccounResponse;

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
		SavingAccDepositeDtls savingAccounDepositeDtls = SavingAccDepositeDtls.builder().amount(savingAccounReq.getAmount())
				.accountNumber(savingAccountEntity).date(LocalDate.now()).isCredit((short) 1).build();
		savingAccounDepositeDtls=this.getDaoManager().getSavingAccDao().saveOrUpdate(savingAccounDepositeDtls);
		return SavingAccounResponse.builder().accountNumber(savingAccountEntity.getAccountNumber()).build();
	}

}
