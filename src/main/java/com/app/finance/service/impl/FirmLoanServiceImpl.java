package com.app.finance.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.app.finance.dto.FirmLoanDto;
import com.app.finance.dto.ShortTermLoanDto;
import com.app.finance.entity.FirmLoanDtls;
import com.app.finance.entity.ShortTermLoan;
import com.app.finance.exception.RecordNotFound;
import com.app.finance.service.DaoServicess;
import com.app.finance.service.FirmLoanService;

@Service
public class FirmLoanServiceImpl extends DaoServicess implements FirmLoanService {

	@Override
	public FirmLoanDto saveOrUpdate(FirmLoanDto firmLoanDto) {
		FirmLoanDtls firmLoanDtls = null;
		if (firmLoanDto.getFirmLoanId() != null) {
			Optional<FirmLoanDtls> firmLoanDtlData = this.getDaoManager().getFirmLoanDao()
					.findByFirmLoanId(firmLoanDto.getFirmLoanId());
			if (firmLoanDtlData.isPresent()) {
				firmLoanDtls = firmLoanDtlData.get();
			}
		}
		if (firmLoanDtls == null) {
			firmLoanDtls = new FirmLoanDtls();
			firmLoanDtls.setIsActive((byte) 1);
			firmLoanDtls.setStartDate(firmLoanDto.getStartDate());
		}
		firmLoanDtls.setLoanFrom(firmLoanDto.getLoanFrom());
		firmLoanDtls.setLoanAmt(firmLoanDto.getLoanAmt());
		if (firmLoanDto.getIsActive() != null && firmLoanDto.getIsActive() == 0) {
			firmLoanDtls.setIsActive(firmLoanDto.getIsActive());
			firmLoanDtls.setClosingDate(LocalDate.now());
		}
		firmLoanDtls.setLastUpdated(LocalDate.now());
		if (firmLoanDto.getInterestAmt() != null)
			firmLoanDtls.setInterestAmt(firmLoanDto.getInterestAmt());
		if (firmLoanDto.getRemark() != null)
			firmLoanDtls.setRemark(firmLoanDto.getRemark());
		firmLoanDtls = this.getDaoManager().getFirmLoanDao().saveOrUpdate(firmLoanDtls);
		firmLoanDto.setFirmLoanId(firmLoanDtls.getFirmLoanId());
		return firmLoanDto;
	}

	@Override
	public List<FirmLoanDto> findAllByStatus(Byte status) {
		List<FirmLoanDtls> firmLoanDtls = this.getDaoManager().getFirmLoanDao().findAllByStatus(status);
		if (firmLoanDtls != null && firmLoanDtls.size() > 0) {
			List<FirmLoanDto> firmLoanDtos = firmLoanDtls.stream().map(firmLoanDtl -> {
				FirmLoanDto firmLoanDto = new FirmLoanDto();
				firmLoanDto.setFirmLoanId(firmLoanDtl.getFirmLoanId());
				firmLoanDto.setLoanFrom(firmLoanDtl.getLoanFrom());
				firmLoanDto.setLoanAmt(firmLoanDtl.getLoanAmt());
				firmLoanDto.setStartDate(firmLoanDtl.getStartDate());
				firmLoanDto.setClosingDate(firmLoanDtl.getClosingDate());
				firmLoanDto.setInterestAmt(firmLoanDtl.getInterestAmt());
				firmLoanDto.setRemark(firmLoanDtl.getRemark());
				return firmLoanDto;
			}).collect(Collectors.toList());
			return firmLoanDtos;
		}
		return null;
	}

	@Override
	public FirmLoanDto findFirmLoanDtlById(Long firmLoanId) {
		if (firmLoanId == null)
			throw new NullPointerException("firmLoanId May Not Be Null");
		Optional<FirmLoanDtls> firmLoanDtls = this.getDaoManager().getFirmLoanDao().findByFirmLoanId(firmLoanId);
		if (!firmLoanDtls.isPresent())
			throw new RecordNotFound("Record Not Found");
		FirmLoanDto firmLoanDto = new FirmLoanDto();
		firmLoanDto.setFirmLoanId(firmLoanDtls.get().getFirmLoanId());
		firmLoanDto.setLoanFrom(firmLoanDtls.get().getLoanFrom());
		firmLoanDto.setLoanAmt(firmLoanDtls.get().getLoanAmt());
		firmLoanDto.setStartDate(firmLoanDtls.get().getStartDate());
		return firmLoanDto;
	}

	@Override
	public ShortTermLoanDto saveOrUpdate(ShortTermLoanDto shortTermLoanDto) {
		ShortTermLoan shortTermLoan = null;
		if (shortTermLoanDto.getShortTermLoanId() != null) {
			Optional<ShortTermLoan> shortTermLoanDtl = this.getDaoManager().getFirmLoanDao()
					.findByShortTermLoanId(shortTermLoanDto.getShortTermLoanId());
			if (shortTermLoanDtl.isPresent()) {
				shortTermLoan = shortTermLoanDtl.get();
			}
		}
		if (shortTermLoan == null) {
			shortTermLoan = new ShortTermLoan();
			shortTermLoan.setStatus((byte) 1);
			shortTermLoan.setStartDate(shortTermLoanDto.getStartDate());
			shortTermLoan.setEndDate(shortTermLoanDto.getEndDate());
		}
		shortTermLoan.setCustFullName(shortTermLoanDto.getCustFullName());
		shortTermLoan.setLoanAmt(shortTermLoanDto.getLoanAmt());
		if (shortTermLoanDto.getStatus() != null && shortTermLoanDto.getStatus() == 0) {
			shortTermLoan.setStatus(shortTermLoanDto.getStatus());
			shortTermLoan.setClosingDate(LocalDate.now());
		}
		if (shortTermLoanDto.getRemark() != null)
			shortTermLoan.setRemark(shortTermLoanDto.getRemark());
		shortTermLoan = this.getDaoManager().getFirmLoanDao().saveOrUpdate(shortTermLoan);
		shortTermLoanDto.setShortTermLoanId(shortTermLoan.getShortTermLoanId());
		return shortTermLoanDto;
	}

	@Override
	public ShortTermLoanDto findShortTermLoanDtlById(Long shortTermLoanId) {
		if (shortTermLoanId == null)
			throw new NullPointerException("shortTermLoanId may not be null");
		ShortTermLoan shortTermLoan = null;
		Optional<ShortTermLoan> shortTermLoanDtl = this.getDaoManager().getFirmLoanDao()
				.findByShortTermLoanId(shortTermLoanId);
		if (!shortTermLoanDtl.isPresent())
			throw new RecordNotFound("Record Not Found");
		shortTermLoan = shortTermLoanDtl.get();
		ShortTermLoanDto shortTermLoanDto = new ShortTermLoanDto();
		shortTermLoanDto.setStatus(shortTermLoan.getStatus());
		shortTermLoanDto.setStartDate(shortTermLoan.getStartDate());
		shortTermLoanDto.setEndDate(shortTermLoan.getEndDate());
		shortTermLoanDto.setCustFullName(shortTermLoan.getCustFullName());
		shortTermLoanDto.setLoanAmt(shortTermLoan.getLoanAmt());

		shortTermLoanDto.setClosingDate(shortTermLoan.getClosingDate());

		shortTermLoanDto.setRemark(shortTermLoan.getRemark());

		shortTermLoanDto.setShortTermLoanId(shortTermLoan.getShortTermLoanId());
		return shortTermLoanDto;
	}

	@Override
	public List<ShortTermLoanDto> findAllShortTermLoanByStatus(Byte status) {
		List<ShortTermLoan> shortTermLoans = this.getDaoManager().getFirmLoanDao().findAllShortTermLoanByStatus(status);
		if (shortTermLoans != null && shortTermLoans.size() > 0) {
			List<ShortTermLoanDto> shortTermLoanDtos = shortTermLoans.stream().map(shortTermLoan -> {
				ShortTermLoanDto shortTermLoanDto = new ShortTermLoanDto();
				shortTermLoanDto.setCustFullName(shortTermLoan.getCustFullName());
				shortTermLoanDto.setShortTermLoanId(shortTermLoan.getShortTermLoanId());
				shortTermLoanDto.setLoanAmt(shortTermLoan.getLoanAmt());
				shortTermLoanDto.setStartDate(shortTermLoan.getStartDate());
				shortTermLoanDto.setEndDate(shortTermLoan.getEndDate());
				shortTermLoanDto.setRemark(shortTermLoan.getRemark());
				return shortTermLoanDto;
			}).collect(Collectors.toList());
			return shortTermLoanDtos;
		}
		return null;
	}

}
