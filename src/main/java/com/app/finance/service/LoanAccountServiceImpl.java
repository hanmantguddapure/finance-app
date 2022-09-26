package com.app.finance.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.app.finance.dto.BaseResponse;
import com.app.finance.dto.LoanAccountDetailDto;
import com.app.finance.dto.LoanCollectionRepo;
import com.app.finance.dto.LoanEMIDetailDto;
import com.app.finance.dto.LoanPaymentDetailDto;
import com.app.finance.dto.LoanPenaltyDto;
import com.app.finance.dto.LoanRepoDto;
import com.app.finance.dto.LoanSummaryDto;
import com.app.finance.entity.CustDetail;
import com.app.finance.entity.LoanAccountDetail;
import com.app.finance.entity.LoanInstallmentsDetail;
import com.app.finance.entity.LoanPenalty;
import com.app.finance.exception.RecordNotFound;
import com.app.finance.utils.CommanUtil;

@Transactional
@Service
public class LoanAccountServiceImpl extends DaoServicess implements LoanAccountService {
	private final Logger logger = LogManager.getLogger(LoanAccountServiceImpl.class);

	@Override
	public LoanAccountDetailDto saveOrUpdateLoanAccount(LoanAccountDetailDto accountDetail) {
		Optional<CustDetail> custPersionalDetail = this.getDaoManager().getCustomerDao()
				.findCustomerDtlById(Long.valueOf(accountDetail.getCustId()));
		if (!custPersionalDetail.isPresent())
			throw new RecordNotFound("Customer Not Found");
		LoanAccountDetail loanAccountDetail = new LoanAccountDetail();
		loanAccountDetail.setCustId(custPersionalDetail.get());
		loanAccountDetail.setPrincipalAmount(accountDetail.getPrincipalAmount());
		loanAccountDetail.setInterest(accountDetail.getInterest());
		loanAccountDetail.setInterestAmt(accountDetail.getInterestAmt());
		loanAccountDetail.setDepositeAmt(accountDetail.getDepositeAmt());
		loanAccountDetail.setProcessingFees(accountDetail.getProcessingFees());
		loanAccountDetail.setLoanAmt(accountDetail.getLoanAmt());
		loanAccountDetail.setLoanStartDate(accountDetail.getLoanStartDate());
		loanAccountDetail.setLoanEndDate(accountDetail.getLoanEndDate());
		loanAccountDetail.setInstallMentType(accountDetail.getInstallMentType());
		loanAccountDetail.setInstallments(accountDetail.getInstallments());
		loanAccountDetail.setInstallmentAmount(accountDetail.getInstallmentAmount());
		loanAccountDetail.setLastUpdated(LocalDate.now());
		loanAccountDetail.setLoanStatus("Opened");
		LoanAccountDetail createdAccountDetail = this.getDaoManager().getLoanSectionDao()
				.saveOrUpdateLoanAccount(loanAccountDetail);
		accountDetail.setLoanAccountNo(createdAccountDetail.getLoanAccountNo());
		return accountDetail;
	}

	@Override
	public LoanAccountDetailDto findByLoanId(Long loanAccountNo) {
		Optional<LoanAccountDetail> accountDetail = this.getDaoManager().getLoanSectionDao()
				.findByLoanId(loanAccountNo);
		if (!accountDetail.isPresent())
			throw new RecordNotFound("Account Not Exist");
		LoanAccountDetailDto accountDetailDto = new LoanAccountDetailDto();
		accountDetailDto.setCustId(accountDetail.get().getCustId().getCustId());
		accountDetailDto.setCustFullName(accountDetail.get().getCustId().getFullName());
		accountDetailDto.setPrincipalAmount(accountDetail.get().getPrincipalAmount());
		accountDetailDto.setInterest(accountDetail.get().getInterest());
		accountDetailDto.setInterestAmt(accountDetail.get().getInterestAmt());
		accountDetailDto.setDepositeAmt(accountDetail.get().getDepositeAmt());
		accountDetailDto.setProcessingFees(accountDetail.get().getProcessingFees());
		accountDetailDto.setLoanAmt(accountDetail.get().getLoanAmt());
		accountDetailDto.setLoanStartDate(accountDetail.get().getLoanStartDate());
		accountDetailDto.setLoanEndDate(accountDetail.get().getLoanEndDate());
		accountDetailDto.setInstallMentType(accountDetail.get().getInstallMentType());
		accountDetailDto.setInstallments(accountDetail.get().getInstallments());
		accountDetailDto.setInstallmentAmount(accountDetail.get().getInstallmentAmount());
		accountDetailDto.setLoanAccountNo(accountDetail.get().getLoanAccountNo());
		accountDetailDto.setTotalCollection(accountDetail.get().getTotalCollection());
		accountDetailDto.setLoanStatus(accountDetail.get().getLoanStatus());
		accountDetailDto.setDisburseAmt(accountDetail.get().getDisburseAmt());
		accountDetailDto.setPaymentMode(accountDetail.get().getPaymentMode());
		accountDetailDto.setRemark(accountDetail.get().getRemark());
		if (null != accountDetail.get().getPaymentDate())
			accountDetailDto.setPaymentDate(String.valueOf(accountDetail.get().getPaymentDate()));
		List<LoanInstallmentsDetail> installmentsDetails = this.getDaoManager().getLoanSectionDao()
				.findEMIByLoanId(accountDetail.get());
		if (installmentsDetails != null && installmentsDetails.size() > 0) {
			List<LoanEMIDetailDto> detailDtos = installmentsDetails.stream().map(installmets -> {
				LoanEMIDetailDto detailDto = new LoanEMIDetailDto();
				detailDto.setPayment(installmets.getPaymentAmount());
				detailDto.setPaymentDate(String.valueOf(installmets.getPaymentDate()));
				detailDto.setPaymentId(installmets.getPaymentId());
				detailDto.setPaymentMethod("Daily");
				return detailDto;
			}).collect(Collectors.toList());
			accountDetailDto.setLoanCollections(detailDtos);
		}
		
		return accountDetailDto;
	}

	@Override
	public List<LoanRepoDto> findByStatusAndDate(String status, String fromDate, String toDate) {

		List<LoanRepoDto> accountDetailRepos = this.getDaoManager().getLoanSectionDao()
				.findByStatusAndDate(status, fromDate, toDate).stream().map(accountDetail -> {
					LoanRepoDto loanRepoDto = new LoanRepoDto();
					List<LoanPenalty> loanPenalties = this.getDaoManager().getLoanSectionDao()
							.findPendaltyByLoanId(accountDetail);
					if (loanPenalties != null && loanPenalties.size() > 0)
						loanRepoDto.setTotalPenalty(
								loanPenalties.stream().mapToDouble(loanPenalty -> loanPenalty.getPenaltyAmt()).sum());
					loanRepoDto.setLoanAccountNo(accountDetail.getLoanAccountNo());
					loanRepoDto.setPrincipalAmount(accountDetail.getPrincipalAmount());
					loanRepoDto.setFullName(accountDetail.getCustId().getFullName());
					loanRepoDto.setLoanAmt(CommanUtil.formatDoubleValue(accountDetail.getLoanAmt()));
					loanRepoDto.setTotalCollection(accountDetail.getTotalCollection());
					if (accountDetail.getTotalCollection() == null)
						accountDetail.setTotalCollection(0.00);
					loanRepoDto.setRemainCollection(accountDetail.getPrincipalAmount()
							- accountDetail.getTotalCollection() - accountDetail.getDepositeAmt());
					loanRepoDto.setDisburseAmt(accountDetail.getDisburseAmt());
					loanRepoDto.setLoanStatus(accountDetail.getLoanStatus());
					loanRepoDto.setProceessingFee(accountDetail.getProcessingFees());
					loanRepoDto.setTotalInterest(
							this.calculateIntrestAmount(loanRepoDto.getPrincipalAmount(), accountDetail.getInterest()));
					loanRepoDto.setTotalEarned(this.calculateEarnedAmount(loanRepoDto.getTotalInterest(),
							loanRepoDto.getProceessingFee(), loanRepoDto.getTotalPenalty()));
					loanRepoDto.setTotalInterest(CommanUtil.formatDoubleValue(loanRepoDto.getTotalInterest()));
					loanRepoDto.setTotalEarned(CommanUtil.formatDoubleValue(loanRepoDto.getTotalEarned()));
					return loanRepoDto;
				}).collect(Collectors.toList());
		return accountDetailRepos;
	}

	@Override
	public List<LoanRepoDto> findAllByCustId(Long custId) {
		Optional<CustDetail> custDetail = this.getDaoManager().getCustomerDao().findCustomerDtlById(custId);
		if (!custDetail.isPresent())
			throw new RecordNotFound("Record Not Found");
		List<LoanRepoDto> accountDetailRepos = this.getDaoManager().getLoanSectionDao().findCustId(custDetail.get())
				.stream().map(accountDetail -> {
					LoanRepoDto loanRepoDto = new LoanRepoDto();
					List<LoanPenalty> loanPenalties = this.getDaoManager().getLoanSectionDao()
							.findPendaltyByLoanId(accountDetail);
					if (loanPenalties != null && loanPenalties.size() > 0)
						loanRepoDto.setTotalPenalty(
								loanPenalties.stream().mapToDouble(loanPenalty -> loanPenalty.getPenaltyAmt()).sum());
					loanRepoDto.setLoanAccountNo(accountDetail.getLoanAccountNo());
					loanRepoDto.setPrincipalAmount(accountDetail.getPrincipalAmount());
					loanRepoDto.setLoanAmt(accountDetail.getLoanAmt());
					loanRepoDto.setLoanStatus(accountDetail.getLoanStatus());
					loanRepoDto.setTotalCollection(accountDetail.getTotalCollection());
					if (accountDetail.getTotalCollection() == null)
						accountDetail.setTotalCollection(0.00);
					loanRepoDto.setRemainCollection(accountDetail.getPrincipalAmount()
							- accountDetail.getTotalCollection() - accountDetail.getDepositeAmt());
					loanRepoDto.setDisburseAmt(accountDetail.getDisburseAmt());
					loanRepoDto.setLoanStatus(accountDetail.getLoanStatus());
					loanRepoDto.setProceessingFee(accountDetail.getProcessingFees());
					loanRepoDto.setTotalInterest(
							this.calculateIntrestAmount(loanRepoDto.getPrincipalAmount(), accountDetail.getInterest()));
					loanRepoDto.setTotalEarned(this.calculateEarnedAmount(loanRepoDto.getTotalInterest(),
							loanRepoDto.getProceessingFee(), loanRepoDto.getTotalPenalty()));
					return loanRepoDto;
				}).collect(Collectors.toList());
		return accountDetailRepos;
	}

	@Override
	public List<LoanAccountDetailDto> findByCustIdAndStatus(Long custId, String status) {
		if (custId == null)
			throw new RecordNotFound("custId May Not Be Empty");
		Optional<CustDetail> custPersionalDetail = this.getDaoManager().getCustomerDao()
				.findCustomerDtlById(Long.valueOf(custId));
		if (!custPersionalDetail.isPresent())

			throw new RecordNotFound("Customer Not Found");
		List<LoanAccountDetailDto> accountDetails = null;
		if (status.equalsIgnoreCase("Opened")) {
			accountDetails = this.getDaoManager().getLoanSectionDao().findCustId(custPersionalDetail.get()).stream()
					.map(loandetail -> {
						LoanAccountDetailDto accountDetailDto = new LoanAccountDetailDto();
						// accountDetailDto.setLoanAccountNo(String.valueOf(loandetail.getLoanAccountNo()));
						/*
						 * accountDetailDto.setPrincipalAmount(String.valueOf(loandetail.
						 * getPrincipalAmount()));
						 * accountDetailDto.setEmiAmount(loandetail.getInstallmentAmount());
						 * accountDetailDto.setTotalCollection(loandetail.getTotalCollection());
						 */
						return accountDetailDto;
					}).collect(Collectors.toList());
		}

		return accountDetails;
	}

	@Override
	public LoanPaymentDetailDto saveOrUpdateLoanDisburserment(LoanPaymentDetailDto paymentDetailDto) {
		Optional<LoanAccountDetail> accountDetail = this.getDaoManager().getLoanSectionDao()
				.findByLoanId(paymentDetailDto.getLoanAccountNo());
		if (!accountDetail.isPresent())
			throw new RecordNotFound("Not Found Loan Account");
		if (accountDetail.get().getDisburseAmt() != null)
			accountDetail.get()
					.setDisburseAmt(accountDetail.get().getDisburseAmt() + paymentDetailDto.getDisburseAmt());
		else
			accountDetail.get().setDisburseAmt(paymentDetailDto.getDisburseAmt());
		accountDetail.get().setPaymentDate(LocalDate.now());
		accountDetail.get().setPaymentMode(paymentDetailDto.getPaymentMode());
		accountDetail.get().setLastUpdated(LocalDate.now());
		this.getDaoManager().getLoanSectionDao().saveOrUpdateLoanAccount(accountDetail.get());
		return paymentDetailDto;
	}

	@Override
	public List<LoanRepoDto> findLoanDisbursementByStatus(String status) {
		if (status == null)
			throw new NullPointerException("status may not be null");
		List<LoanRepoDto> accountDetailRepos = this.getDaoManager().getLoanSectionDao().findByStatus(status).stream()
				.filter(loanDtls -> loanDtls.getPaymentDate() != null).map(accountDetail -> {
					LoanRepoDto loanRepoDto = new LoanRepoDto();
					loanRepoDto.setFullName(accountDetail.getCustId().getFullName());
					loanRepoDto.setLoanAccountNo(accountDetail.getLoanAccountNo());
					loanRepoDto.setPrincipalAmount(accountDetail.getPrincipalAmount());
					loanRepoDto.setLoanAmt(accountDetail.getLoanAmt());
					loanRepoDto.setPaymentMode(accountDetail.getPaymentMode());
					loanRepoDto.setDisburseAmt(accountDetail.getDisburseAmt());
					loanRepoDto.setPaymentDate(String.valueOf(accountDetail.getPaymentDate()));
					return loanRepoDto;
				}).collect(Collectors.toList());
		return accountDetailRepos;
	}

	@Override
	public List<LoanPaymentDetailDto> findPendingDisbursements() {
		List<LoanAccountDetail> openedAccountDetailLst = this.getDaoManager().getLoanSectionDao()
				.findByStatus("Opened");
		if (openedAccountDetailLst != null && openedAccountDetailLst.size() > 0) {
			List<LoanPaymentDetailDto> loanPendingPaymentDetails = openedAccountDetailLst.stream()
					.filter(paymentDate -> paymentDate.getPaymentDate() == null).map(pendingLoanPayment -> {
						LoanPaymentDetailDto loanPaymentDetailDto = new LoanPaymentDetailDto();
						loanPaymentDetailDto.setCustFullName(pendingLoanPayment.getCustId().getFullName());
						loanPaymentDetailDto.setLoanAmt(pendingLoanPayment.getLoanAmt());
						loanPaymentDetailDto.setLoanAccountNo(pendingLoanPayment.getLoanAccountNo());
						return loanPaymentDetailDto;

					}).collect(Collectors.toList());
			return loanPendingPaymentDetails;
		}

		return null;
	}

	@Override
	public int closeLoanAccount(String status, String remark, Long loanAccountNo) {
		if (loanAccountNo == null)
			throw new NullPointerException("Loan Account Number May Not Be Null");
		Optional<LoanAccountDetail> accountDetail = this.getDaoManager().getLoanSectionDao()
				.findByLoanId(loanAccountNo);
		if (!accountDetail.isPresent())
			throw new RecordNotFound("Record Not Found");
		int i = this.getDaoManager().getLoanSectionDao().closeLoanAccount(status, LocalDate.now(), remark,
				loanAccountNo);
		return i;
	}

	@Override
	public LoanEMIDetailDto saveOrUpdateEMI(LoanEMIDetailDto emiDetail) {
		Optional<LoanAccountDetail> accountDetail = this.getDaoManager().getLoanSectionDao()
				.findByLoanId(emiDetail.getLoanAccNo());
		if (!accountDetail.isPresent())
			throw new RecordNotFound("Account Details Not Found");
		LoanInstallmentsDetail loanEMIDetail = new LoanInstallmentsDetail();
		loanEMIDetail.setLoanAccouuntNo(accountDetail.get());
		loanEMIDetail.setPaymentAmount(emiDetail.getPayment());
		loanEMIDetail.setPaymentMode(emiDetail.getPaymentMethod());
		loanEMIDetail.setPaymentDate(LocalDate.parse(emiDetail.getPaymentDate()));
		loanEMIDetail = this.getDaoManager().getLoanSectionDao().saveOrUpdateEMI(loanEMIDetail);
		Double currCollection = accountDetail.get().getTotalCollection() != null
				? accountDetail.get().getTotalCollection()
				: 0.00;
		currCollection = currCollection + Double.valueOf(emiDetail.getPayment());
		accountDetail.get().setTotalCollection(currCollection);
		this.getDaoManager().getLoanSectionDao().saveOrUpdateLoanAccount(accountDetail.get());
		emiDetail.setPaymentId(loanEMIDetail.getPaymentId());
		return emiDetail;
	}
	@Override
	public BaseResponse deleteEmiByPaymentId(Long paymentId) {
		BaseResponse response=new BaseResponse();
		LoanInstallmentsDetail loanEMIDetail=this.getDaoManager().getLoanSectionDao().findBypaymentId(paymentId);
		Optional<LoanAccountDetail> accountDetail = this.getDaoManager().getLoanSectionDao()
				.findByLoanId(loanEMIDetail.getLoanAccouuntNo().getLoanAccountNo());
		loanEMIDetail.setIsDelete((short) 1);
		loanEMIDetail = this.getDaoManager().getLoanSectionDao().saveOrUpdateEMI(loanEMIDetail);
		Double currCollection = accountDetail.get().getTotalCollection() != null
				? accountDetail.get().getTotalCollection()
				: 0.00;
		currCollection = currCollection - loanEMIDetail.getPaymentAmount();
		accountDetail.get().setTotalCollection(currCollection);
		this.getDaoManager().getLoanSectionDao().saveOrUpdateLoanAccount(accountDetail.get());
		response.setMsg("Successfull");
		return response;
	}

	@Override
	public List<LoanCollectionRepo> findLoanEMIByFromDateAndToDate(LocalDate fromDate, LocalDate toDate) {
		if (fromDate == null && toDate == null)
			throw new NullPointerException("fromDate/toDate may not me null/empty");
		List<LoanCollectionRepo> loanCollectionlst = this.getDaoManager().getLoanSectionDao()
				.findLoanEMIByFromDateAndToDate(fromDate, toDate).stream().map(loanInstallmentDtl -> {
					LoanCollectionRepo collectionRepo = new LoanCollectionRepo();
					collectionRepo.setFullName(loanInstallmentDtl.getLoanAccouuntNo().getCustId().getFullName());
					collectionRepo.setAccNo(loanInstallmentDtl.getLoanAccouuntNo().getLoanAccountNo());
					collectionRepo.setPayment(loanInstallmentDtl.getPaymentAmount());
					collectionRepo.setPaymentDate(loanInstallmentDtl.getPaymentDate());
					return collectionRepo;
				}).collect(Collectors.toList());
		return loanCollectionlst;
	}

	@Override
	public List<LoanCollectionRepo> findAllEMIByDaily() {
		List<LoanInstallmentsDetail> installmentsDetails = this.getDaoManager().getLoanSectionDao()
				.findEMIByPaymentDate(LocalDate.now());
		if (installmentsDetails != null && installmentsDetails.size() > 0) {
			List<LoanCollectionRepo> collectionRepos = installmentsDetails.stream().map(installMentData -> {
				LoanCollectionRepo loanCollectionRepo = new LoanCollectionRepo();
				loanCollectionRepo.setAccNo(installMentData.getLoanAccouuntNo().getLoanAccountNo());
				loanCollectionRepo.setFullName(installMentData.getLoanAccouuntNo().getCustId().getFullName());
				loanCollectionRepo.setPayment(installMentData.getPaymentAmount());
				return loanCollectionRepo;
			}).collect(Collectors.toList());
			return collectionRepos;
		}
		return null;
	}

	@Override
	public LoanPenaltyDto saveOrUpdatePenalty(LoanPenaltyDto loanPenaltyDto) {
		Optional<LoanAccountDetail> accountDetail = this.getDaoManager().getLoanSectionDao()
				.findByLoanId(loanPenaltyDto.getLoanAccountId());
		if (!accountDetail.isPresent())
			throw new RecordNotFound("Loan Account Not Exist");
		LoanPenalty loanPenalty = new LoanPenalty();
		loanPenalty.setLoanAccountId(accountDetail.get());
		loanPenalty.setPenaltyAmt(loanPenaltyDto.getPenaltyAmt());
		loanPenalty.setPenaltyDate(LocalDate.now());
		if (loanPenaltyDto.getRemark() != null)
			loanPenalty.setRemark(loanPenaltyDto.getRemark());
		loanPenalty = this.getDaoManager().getLoanSectionDao().saveOrUpdatePenalty(loanPenalty);
		loanPenaltyDto.setLoanPenaltyId(loanPenalty.getLoanPenaltyId());
		loanPenaltyDto.setPenaltyDate(loanPenalty.getPenaltyDate());
		return loanPenaltyDto;
	}

	@Override
	public List<LoanPenaltyDto> findPendaltyByLoanId(Long accountId) {
		if (accountId == null)
			throw new NullPointerException("accountId may not be null");
		Optional<LoanAccountDetail> accountDetail = this.getDaoManager().getLoanSectionDao().findByLoanId(accountId);
		if (!accountDetail.isPresent())
			throw new RecordNotFound("Loan Account Not Exist");
		List<LoanPenalty> loanPenalties = this.getDaoManager().getLoanSectionDao()
				.findPendaltyByLoanId(accountDetail.get());
		if (loanPenalties == null || loanPenalties.size() < 0)
			throw new RecordNotFound("Record Not Exist");
		List<LoanPenaltyDto> penaltyDtos = loanPenalties.stream().map(pendaltDtl -> {
			LoanPenaltyDto loanPenaltyDto = new LoanPenaltyDto();
			loanPenaltyDto.setLoanPenaltyId(pendaltDtl.getLoanPenaltyId());
			loanPenaltyDto.setPenaltyAmt(pendaltDtl.getPenaltyAmt());
			loanPenaltyDto.setPenaltyDate(pendaltDtl.getPenaltyDate());
			if (pendaltDtl.getRemark() != null)
				loanPenaltyDto.setRemark(pendaltDtl.getRemark());
			return loanPenaltyDto;
		}).collect(Collectors.toList());
		return penaltyDtos;
	}

	@Override
	public LoanSummaryDto getLoanSummaryReportByDate(LocalDate loanStartDate) {
		List<LoanAccountDetail> accountDetails = this.getDaoManager().getLoanSectionDao()
				.findByLoanStartDate(loanStartDate);
		LoanSummaryDto loanSummaryDto = new LoanSummaryDto();
		if (accountDetails != null && accountDetails.size() > 0) {
			// loanSummaryDto.setNewAccounts(accountDetails.stream().count());
			loanSummaryDto.setLoanAmounts(accountDetails.stream()
					.collect(Collectors.summingDouble(accountDetail -> accountDetail.getPrincipalAmount())));
			loanSummaryDto.setDisbursements(
					accountDetails.stream().filter(accountDetail -> accountDetail.getPaymentDate() != null)
							.mapToDouble(accountDetail -> accountDetail.getDisburseAmt()).sum());

		}

		return loanSummaryDto;
	}

	@Override
	public LoanSummaryDto getLoanSummaryReportByStaus(String status) {

		List<LoanAccountDetail> accountDetails = this.getDaoManager().getLoanSectionDao().findByStatus(status);
		if (accountDetails != null && accountDetails.size() > 0) {
			LoanSummaryDto loanSummaryDto = new LoanSummaryDto();
			List<LoanPenalty> loanPenalties = this.getDaoManager().getLoanSectionDao()
					.findPenaltyByLoanAccountIdIn(accountDetails);
			loanSummaryDto.setNoOfaccounts(accountDetails.stream().collect(Collectors.counting()));
			loanSummaryDto.setCollections(
					accountDetails.stream().filter(totalCollection -> totalCollection.getTotalCollection() != null)
							.mapToDouble(totalCollection -> totalCollection.getTotalCollection()).sum());
			loanSummaryDto.setDisbursements(
					accountDetails.stream().filter(disburseAmount -> disburseAmount.getDisburseAmt() != null)
							.mapToDouble(disburseAmount -> disburseAmount.getDisburseAmt()).sum());
			loanSummaryDto.setPrincepleAmount(
					accountDetails.stream().mapToDouble(princeAmount -> princeAmount.getPrincipalAmount()).sum());
			loanSummaryDto.setLoanIntrest(CommanUtil.formatDoubleValue(accountDetails.stream().mapToDouble(loanIntrest ->loanIntrest.getInterestAmt()).sum()));
			loanSummaryDto.setProcessingFees(accountDetails.stream().mapToDouble(processFees ->processFees.getProcessingFees()).sum());
			loanSummaryDto.setPendingDisburement((double) 0);
			accountDetails.forEach(accountDetail -> {
	
				if (accountDetail.getDisburseAmt() == null)
					accountDetail.setDisburseAmt((double) 0);
				loanSummaryDto.setPendingDisburement(loanSummaryDto.getPendingDisburement()
						+ (accountDetail.getLoanAmt() - accountDetail.getDisburseAmt()));
			});
			if (loanPenalties != null && loanPenalties.size() > 0)
				loanSummaryDto.setPenalty(
						loanPenalties.stream().mapToDouble(loanPenalty -> loanPenalty.getPenaltyAmt()).sum());
			;
			return loanSummaryDto;
		}
		return null;
	}

	@Override
	public List<LoanPenaltyDto> findAllPendaltiesByLoanStatus(String loanStatus) {
		List<LoanAccountDetail> accountDetails = this.getDaoManager().getLoanSectionDao().findByStatus(loanStatus);
		if (accountDetails == null || accountDetails.size() < 0)
			throw new NullPointerException("List is Empty");
		List<LoanPenalty> loanPenalties = this.getDaoManager().getLoanSectionDao()
				.findPenaltyByLoanAccountIdIn(accountDetails);
		if (loanPenalties != null && loanPenalties.size() > 0) {
			List<LoanPenaltyDto> loanPenaltyDtos = loanPenalties.stream().map(loanPenalty -> {
				LoanPenaltyDto loanPenaltyDto = new LoanPenaltyDto();
				loanPenaltyDto.setLoanAccountId(loanPenalty.getLoanAccountId().getLoanAccountNo());
				loanPenaltyDto.setCustFullName(loanPenalty.getLoanAccountId().getCustId().getFullName());
				loanPenaltyDto.setPenaltyAmt(loanPenalty.getPenaltyAmt());
				loanPenaltyDto.setRemark(loanPenalty.getRemark());
				return loanPenaltyDto;
			}).collect(Collectors.toList());
			return loanPenaltyDtos;
		}
		return null;
	}

	@Override
	public Boolean checkDuplicatePayment(Long loanId, LocalDate paymentDate) {
		boolean flag = false;
		if (loanId == null || paymentDate == null)
			throw new NullPointerException("loanId/paymentDate may not be null");
		Optional<LoanAccountDetail> accountDetail = this.getDaoManager().getLoanSectionDao().findByLoanId(loanId);
		if (!accountDetail.isPresent())
			throw new RecordNotFound("Record Not Exist");
		List<LoanInstallmentsDetail> installmentsDetail = this.getDaoManager().getLoanSectionDao()
				.findLoanInstallmentsByLoanIdAndPaymentDate(accountDetail.get(), paymentDate);
		if (installmentsDetail != null && installmentsDetail.size() > 0)
			flag = true;
		return flag;
	}

	@Override
	public Boolean autoCloseLoanAccount() {
		logger.info(" auto closing pocess begins");
		List<LoanAccountDetail> openedAccountDetails = this.getDaoManager().getLoanSectionDao().findByStatus("Opened");
		openedAccountDetails.stream().forEach(accoundDetail -> {
			Double pendigAmount = accoundDetail.getPrincipalAmount() - accoundDetail.getTotalCollection()
					- accoundDetail.getDepositeAmt();
			if (pendigAmount <= 0) {
				this.closeLoanAccount("Closed", "Closed By System", accoundDetail.getLoanAccountNo());
				logger.info("--system closed account" + accoundDetail.getLoanAccountNo());
			}
		});
		return true;
	}

	private Double calculateEarnedAmount(Double intrestAmt, Double processingFees, Double penalty) {
		double earned = 0;
		if (intrestAmt != null)
			earned = earned + intrestAmt;
		if (processingFees != null)
			earned = earned + processingFees;
		if (penalty != null)
			earned = earned + penalty;
		return earned;
	}

	private Double calculateIntrestAmount(Double princepleAmt, Float interest) {
		return (princepleAmt / 100) * interest;
	}


}
