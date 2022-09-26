package com.app.finance.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.app.finance.entity.CustDetail;
import com.app.finance.entity.LoanAccountDetail;
import com.app.finance.entity.LoanInstallmentsDetail;
import com.app.finance.entity.LoanPenalty;

public interface LoanAccountDao {
	public LoanAccountDetail saveOrUpdateLoanAccount(LoanAccountDetail accountDetail);

	public Optional<LoanAccountDetail> findByLoanId(Long loanAccid);

	public List<LoanAccountDetail> findCustId(CustDetail custId);

	public List<LoanAccountDetail> findByStatus(String status);

	public List<LoanAccountDetail> findByLoanStartDate(LocalDate loanStartDate);

	public int closeLoanAccount(String status, LocalDate lastUpdated, String remark, Long loanAccountNo);

	public LoanInstallmentsDetail saveOrUpdateEMI(LoanInstallmentsDetail emiDetail);

	public List<LoanInstallmentsDetail> findLoanInstallmentsByLoanIdAndPaymentDate(LoanAccountDetail loanId,
			LocalDate paymentDate);

	public List<LoanInstallmentsDetail> findLoanEMIByFromDateAndToDate(LocalDate fromDate, LocalDate toDate);

	public List<LoanInstallmentsDetail> findEMIByLoanId(LoanAccountDetail loanAccountDetail);

	public List<LoanInstallmentsDetail> findEMIByLoanAccouuntNos(List<LoanAccountDetail> accountDetail);

	public List<LoanInstallmentsDetail> findEMIByPaymentDate(LocalDate paymentDate);

	public LoanPenalty saveOrUpdatePenalty(LoanPenalty loanPenalty);

	public List<LoanPenalty> findPendaltyByLoanId(LoanAccountDetail accountDetail);

	public List<LoanPenalty> findPenaltyByLoanAccountIdIn(List<LoanAccountDetail> accountDetail);
	
	public LoanInstallmentsDetail findBypaymentId(Long paymentId);
	public List<LoanAccountDetail> findByStatusAndDate(String status, String fromDate, String toDate);
}
