package com.app.finance.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.app.finance.controller.ControllerManager;
import com.app.finance.dto.AppSummaryDto;
import com.app.finance.dto.DashBoardRepo;
import com.app.finance.dto.ExpenseDto;
import com.app.finance.dto.FDSummaryDto;
import com.app.finance.dto.FirmLoanDto;
import com.app.finance.dto.LoanSummaryDto;
import com.app.finance.dto.ShortTermLoanDto;
import com.app.finance.service.DashBoardService;

@Service
public class DashBoardServiceImpl extends ControllerManager implements DashBoardService {
	/*
	 * @Autowired LoanAccountService loanAccountService;
	 * 
	 * @Autowired FDAccountService fdAccountService;
	 */
	@Override
	public DashBoardRepo getDashBoardData() {
		DashBoardRepo dashBoardRepo = new DashBoardRepo();
		/*
		 * List<LoanAccountDetail> accountDetails =
		 * this.getDaoManager().getLoanSectionDao().findByStatus("Opened");
		 */
		/*
		 * List<LoanInstallmentsDetail> installmentsDetails =
		 * this.getDaoManager().getLoanSectionDao()
		 * .findEMIByLoanAccouuntNos(accountDetails);
		 */
		/*
		 * Double totalOpenedLoanCollection = accountDetails.stream()
		 * .filter(totalCollection -> totalCollection.getTotalCollection() != null)
		 * .mapToDouble(totalCollection -> totalCollection.getTotalCollection()).sum();
		 */
		/* Long count = accountDetails.stream().collect(Collectors.counting()); */
		/*
		 * Double totalPrinceAmount = accountDetails.stream() .mapToDouble(princeAmount
		 * -> princeAmount.getPrincipalAmount()).sum();
		 */
		/*
		 * Double totalDisburesment = accountDetails.stream() .filter(disburseAmount ->
		 * disburseAmount.getDisburseAmt() != null) .mapToDouble(disburseAmount ->
		 * disburseAmount.getDisburseAmt()).sum();
		 */
		/*
		 * dashBoardRepo.setPendingDisbursement((double) 0);
		 * accountDetails.forEach(accountDetail -> { if (accountDetail.getDisburseAmt()
		 * == null) accountDetail.setDisburseAmt((double) 0);
		 * dashBoardRepo.setPendingDisbursement(dashBoardRepo.getPendingDisbursement() +
		 * (accountDetail.getLoanAmt() - accountDetail.getDisburseAmt())); });
		 */
		/*
		 * Double todayCollection = installmentsDetails.stream()
		 * .mapToDouble(installmentsDetail ->
		 * installmentsDetail.getPaymentAmount()).sum();
		 */
		// if (installmentsDetails != null && installmentsDetails.size() > 0)
		// dashBoardRepo.setTodayTotalCollection(todayCollection);
		/* dashBoardRepo.setTotalOpenedLoanAccount(count); */
		/* dashBoardRepo.setTotalActiveLoanCollection(totalOpenedLoanCollection); */
		// dashBoardRepo.setTotalRunningLoanAmt(totalPrinceAmount);
		/* dashBoardRepo.setTotalDisburesements(totalDisburesment); */

		// dashBoardRepo.setPendingCollections(totalRunningLoanAmt -
		// totalOpenedLoanCollection);
		/*
		 * List<FDAccount> fdAccounts =
		 * this.getDaoManager().getFdAccountDao().findByIsActive((byte) 1);
		 */
		/*
		 * List<FDInterest> fdInterests = this.getDaoManager().getFdAccountDao()
		 * .findPaidInterestByFdAccountNos(fdAccounts);
		 */
		/* Long totalFdAccounts = fdAccounts.stream().collect(Collectors.counting()); */
		/*
		 * Double totalFdAmount = fdAccounts.stream().mapToDouble(fdAccount ->
		 * fdAccount.getAmount()).sum();
		 */
		/*
		 * Double fdPaidInterest = fdInterests.stream().mapToDouble(fdInterest ->
		 * fdInterest.getPaidInterest()).sum();
		 */
		/* dashBoardRepo.setFdPaidInterest(fdPaidInterest); */
		/*
		 * dashBoardRepo.setTotalFdAmount(totalFdAmount);
		 * dashBoardRepo.setTotalActiveFdAcc(totalFdAccounts);
		 */

		return dashBoardRepo;
	}

	@Override
	public AppSummaryDto getAllSummaryReport(String loanstatus, String fdStatus) {
		AppSummaryDto appSummaryDto = new AppSummaryDto();
		LoanSummaryDto loanSummaryDto = this.getServiceManager().getLoanService()
				.getLoanSummaryReportByStaus(loanstatus);
		if (loanSummaryDto != null)
			appSummaryDto.setLoanSummary(loanSummaryDto);
		FDSummaryDto fdSummaryDto = this.getServiceManager().getFdAccountService().getFDSummaryByStatus(fdStatus);
		List<ExpenseDto> expensess = this.getServiceManager().getExpenseService()
				.findExpensesByFromDate(LocalDate.now());
		if (expensess != null && expensess.size() > 0) {
			appSummaryDto.setExpenses(expensess.stream().mapToDouble(expenses -> expenses.getAmount()).sum());
		}
		List<FirmLoanDto> firmLoanDtos = this.getServiceManager().getFirmLoanService().findAllByStatus((byte) 1);
		if (firmLoanDtos != null && firmLoanDtos.size() > 0) {
			appSummaryDto.setFirmLoan(firmLoanDtos.stream().mapToDouble(firmLoan -> firmLoan.getLoanAmt()).sum());
		}
		
		if (fdSummaryDto != null) {
			appSummaryDto.setFdSummary(fdSummaryDto);
		}

		List<ShortTermLoanDto> shortTermLoans = this.getServiceManager().getFirmLoanService()
				.findAllShortTermLoanByStatus((byte) 1);
		if (shortTermLoans != null && shortTermLoans.size() > 0)
			appSummaryDto.setShortTermLoan(
					shortTermLoans.stream().mapToDouble(shortTermLoan -> shortTermLoan.getLoanAmt()).sum());
		return appSummaryDto;

	}

}
