package com.app.finance.service;

import com.app.finance.dto.AppSummaryDto;
import com.app.finance.dto.DashBoardRepo;

public interface DashBoardService {
	public DashBoardRepo getDashBoardData();

	public AppSummaryDto getAllSummaryReport(String loanstatus,String fdStatus);
}
