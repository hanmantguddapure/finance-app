package com.app.finance.service;

import java.time.LocalDate;
import java.util.List;

import com.app.finance.dto.BaseResponse;
import com.app.finance.dto.LoanAccountDetailDto;
import com.app.finance.dto.LoanCollectionRepo;
import com.app.finance.dto.LoanEMIDetailDto;
import com.app.finance.dto.LoanPaymentDetailDto;
import com.app.finance.dto.LoanPenaltyDto;
import com.app.finance.dto.LoanRepoDto;
import com.app.finance.dto.LoanSummaryDto;

public interface LoanAccountService {
	public LoanAccountDetailDto saveOrUpdateLoanAccount(LoanAccountDetailDto accountDetail);

	public LoanAccountDetailDto findByLoanId(Long loanAccountNo);

	public List<LoanRepoDto> findByStatusAndDate(String status, String fromDate, String toDate);

	public List<LoanRepoDto> findAllByCustId(Long custId);

	public List<LoanAccountDetailDto> findByCustIdAndStatus(Long custId, String status);

	public int closeLoanAccount(String status, String remark, Long loanAccountNo);

	public Boolean autoCloseLoanAccount();

	public LoanEMIDetailDto saveOrUpdateEMI(LoanEMIDetailDto emiDetail);
	
	public BaseResponse deleteEmiByPaymentId(Long paymentId);

	public Boolean checkDuplicatePayment(Long loanId, LocalDate paymenDate);

	public List<LoanCollectionRepo> findLoanEMIByFromDateAndToDate(LocalDate fromDate, LocalDate toDate);

	public List<LoanCollectionRepo> findAllEMIByDaily();

	public LoanPaymentDetailDto saveOrUpdateLoanDisburserment(LoanPaymentDetailDto paymentDetailDto);

	public List<LoanRepoDto> findLoanDisbursementByStatus(String status);

	public List<LoanPaymentDetailDto> findPendingDisbursements();

	public LoanPenaltyDto saveOrUpdatePenalty(LoanPenaltyDto loanPenaltyDto);

	public List<LoanPenaltyDto> findPendaltyByLoanId(Long accountId);

	public List<LoanPenaltyDto> findAllPendaltiesByLoanStatus(String loanStatus);

	public LoanSummaryDto getLoanSummaryReportByDate(LocalDate date);

	public LoanSummaryDto getLoanSummaryReportByStaus(String status);
}
