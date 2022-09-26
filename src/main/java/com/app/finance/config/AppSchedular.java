package com.app.finance.config;

import java.time.LocalDateTime;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.app.finance.service.LoanAccountService;

@Component
public class AppSchedular {
	private final Logger logger = LogManager.getLogger(AppSchedular.class);

	@Autowired
	private LoanAccountService loanService;
	/* Run every day at 6.46 pm */
	@Scheduled(cron = "0 46 18 * * *")
	public void closeLoans() {
		logger.info("--System Loan Closing started at--" + LocalDateTime.now());
		loanService.autoCloseLoanAccount();
		logger.info("--Task Finished at--"+LocalDateTime.now());
	}

}
