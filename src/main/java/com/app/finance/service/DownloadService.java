package com.app.finance.service;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;

public interface DownloadService {
	//public ResponseEntity<?> customerPdfDownload(Long custId) throws IOException;

	public ResponseEntity<?> downloandLoanAccounts(String status);

	public ResponseEntity<?> downloandLoanAccountDtl(Long accountId);

	public ResponseEntity<?> downloandLoanCollectionRepo(LocalDate fromDate, LocalDate toDate);

	public ResponseEntity<?> downloandCustomerFD(Long fdId);
}
