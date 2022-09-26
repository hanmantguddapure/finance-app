package com.app.finance.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.app.finance.entity.LoanAccountDetail;
import com.app.finance.entity.LoanInstallmentsDetail;

public interface LoanEMIDetailRepo extends CrudRepository<LoanInstallmentsDetail, Long> {
	public List<LoanInstallmentsDetail> getLoanInstallmentsByLoanAccouuntNo(LoanAccountDetail accountDetail);

	public List<LoanInstallmentsDetail> findLoanInstallmentsByLoanAccouuntNoIn(List<LoanAccountDetail> accountDetail);

	public List<LoanInstallmentsDetail> findByPaymentDateBetween(LocalDate fromDate, LocalDate toDate);

	@Query("from LoanInstallmentsDetail where paymentDate=:paymentDate")
	public List<LoanInstallmentsDetail> getInstallmentDetailsByDate(@Param("paymentDate") LocalDate paymentDate);

	public List<LoanInstallmentsDetail> findByLoanAccouuntNoAndPaymentDate(LoanAccountDetail loanId,
			LocalDate paymentDate);
	
	public LoanInstallmentsDetail findBypaymentId(Long paymentId);
}
