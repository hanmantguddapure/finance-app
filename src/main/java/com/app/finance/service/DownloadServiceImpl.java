package com.app.finance.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.finance.controller.ControllerManager;
import com.app.finance.dto.FDAccountDto;
import com.app.finance.dto.LoanAccountDetailDto;
import com.app.finance.dto.LoanCollectionRepo;
import com.app.finance.dto.LoanRepoDto;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class DownloadServiceImpl extends ControllerManager implements DownloadService {
	private static final Logger logger = LogManager.getLogger(DownloadServiceImpl.class);
	ByteArrayOutputStream byteArrayOutStream = new ByteArrayOutputStream();
	HashMap<String, Object> pdfData = new HashMap<>();
	// private String fileName = "";
	/*
	 * @Override public ResponseEntity<?> customerPdfDownload(Long custId) throws
	 * IOException { CustomerDtlsRequest customerDto =
	 * this.getServiceManager().getCustomerService().findCustomerDtlById(custId);
	 * List<CustContactPeopleReq> contactPersionDtos =
	 * null;this.getServiceManager().getCustomerService()
	 * .findContactPersionsByCustId(custId); ByteArrayInputStream bis =
	 * generatePdf(customerDto, contactPersionDtos); HttpHeaders headers = new
	 * HttpHeaders(); headers.add("Content-Disposition", "inline; filename=" +
	 * "customer_" + customerDto.getFullName() + ".pdf"); return
	 * ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
	 * .body(new InputStreamResource(bis)); }
	 */

	@Override
	public ResponseEntity<?> downloandLoanAccounts(String status) {
		List<LoanRepoDto> loanRepolst = this.getServiceManager().getLoanService().findByStatusAndDate(status,null,null);
		ByteArrayInputStream bis = generateLoanAccountsPdf(loanRepolst);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=" + "LoanReport_" + LocalDate.now() + ".pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));

	}

	@Override
	public ResponseEntity<?> downloandCustomerFD(Long fdId) {
		FDAccountDto fdAccountDto = this.getServiceManager().getFdAccountService().findByAccountId(fdId);
		ByteArrayInputStream bis = generateCustomerFDPDF(fdAccountDto);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=" + "CustFD_" + LocalDate.now() + ".pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}

	@Override
	public ResponseEntity<?> downloandLoanAccountDtl(Long accountId) {
		LoanAccountDetailDto loanAccountDtls = this.getServiceManager().getLoanService().findByLoanId(accountId);
		ByteArrayInputStream bis = generateLoanAccountDetailPDF(loanAccountDtls);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=" + "LoanAccountDtl_" + loanAccountDtls.getLoanAccountNo()
				+ LocalDate.now() + ".pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}

	@Override
	public ResponseEntity<?> downloandLoanCollectionRepo(LocalDate fromDate, LocalDate toDate) {
		List<LoanCollectionRepo> collectionRepos = this.getServiceManager().getLoanService()
				.findLoanEMIByFromDateAndToDate(fromDate, toDate);
		ByteArrayInputStream bis = generateLoanCollectionRepoPDF(collectionRepos);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=" + "LoanCollectionRepo" + LocalDate.now() + ".pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));

	}

	private ByteArrayInputStream generateLoanCollectionRepoPDF(List<LoanCollectionRepo> collectionRepos) {
		Document document = new Document(PageSize.A4, 0, 0, 50, 50);
		Font tableHeadingFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14f, Font.BOLD);
		Font tableColHeadingFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12f, Font.BOLD);
		Font leftColumnHeadingFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10f, Font.NORMAL);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			PdfPTable collectionDttlable = createPdfTable(4, new float[] { 5, 35, 30, 30 }, 1);
			collectionDttlable.addCell(getCell("Collection Details", 4, tableHeadingFont));
			collectionDttlable.addCell(getCell("Sr.No.", 0, tableColHeadingFont));
			collectionDttlable.addCell(getCell("Customer Name", 0, tableColHeadingFont));
			collectionDttlable.addCell(getCell("Amount", 0, tableColHeadingFont));
			collectionDttlable.addCell(getCell("Date", 0, tableColHeadingFont));
			PdfWriter.getInstance(document, out);
			if (collectionRepos != null && collectionRepos.size() > 0) {
				AtomicInteger srNo = new AtomicInteger(1);
				collectionRepos.stream().forEach(data -> {
					collectionDttlable
							.addCell(getCell(String.valueOf(srNo.getAndIncrement()), 0, leftColumnHeadingFont));
					collectionDttlable.addCell(getCell(String.valueOf(data.getFullName()), 0, leftColumnHeadingFont));
					collectionDttlable.addCell(getCell(String.valueOf(data.getPayment()), 0, leftColumnHeadingFont));
					collectionDttlable
							.addCell(getCell(String.valueOf(data.getPaymentDate()), 0, leftColumnHeadingFont));
				});
			}

			document.open();
			document.add(collectionDttlable);
			document.close();
		} catch (DocumentException ex) {
			logger.error("exception occured ", ex);
		}

		return new ByteArrayInputStream(out.toByteArray());
	}

	private ByteArrayInputStream generateLoanAccountDetailPDF(LoanAccountDetailDto loanAccountDtls) {
		Document document = new Document(PageSize.A4, 0, 0, 50, 50);
		Font tableHeadingFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14f, Font.BOLD);
		Font tableColHeadingFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12f, Font.BOLD);
		Font leftColumnHeadingFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10f, Font.NORMAL);
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {
			PdfPTable loanDtltable = createPdfTable(2, new float[] { 10, 250 }, 1);
			loanDtltable.addCell(getCell("Account Detail", 2, tableHeadingFont));
			loanDtltable.addCell(getCell("Account Number", 0, leftColumnHeadingFont));
			loanDtltable.addCell(getCell(String.valueOf(loanAccountDtls.getLoanAccountNo()), 0, leftColumnHeadingFont));
			loanDtltable.addCell(getCell("Name", 0, leftColumnHeadingFont));
			loanDtltable.addCell(getCell(loanAccountDtls.getCustFullName(), 0, leftColumnHeadingFont));
			loanDtltable.addCell(getCell("Duration", 0, leftColumnHeadingFont));
			loanDtltable.addCell(getCell(loanAccountDtls.getLoanStartDate() + " To " + loanAccountDtls.getLoanEndDate(),
					0, leftColumnHeadingFont));
			loanDtltable.addCell(getCell("Loan Amount", 0, leftColumnHeadingFont));
			loanDtltable
					.addCell(getCell(String.valueOf(loanAccountDtls.getPrincipalAmount()), 0, leftColumnHeadingFont));
			loanDtltable.addCell(getCell("Disbursement", 0, leftColumnHeadingFont));
			loanDtltable.addCell(getCell(String.valueOf(loanAccountDtls.getLoanAmt()), 0, leftColumnHeadingFont));
			loanDtltable.addCell(getCell("Intrest %", 0, leftColumnHeadingFont));
			loanDtltable.addCell(getCell(String.valueOf(loanAccountDtls.getInterest()), 0, leftColumnHeadingFont));
			loanDtltable.addCell(getCell("Processing Fees", 0, leftColumnHeadingFont));
			loanDtltable
					.addCell(getCell(String.valueOf(loanAccountDtls.getProcessingFees()), 0, leftColumnHeadingFont));
			loanDtltable.addCell(getCell("Deposite", 0, leftColumnHeadingFont));
			loanDtltable.addCell(getCell(String.valueOf(loanAccountDtls.getDepositeAmt()), 0, leftColumnHeadingFont));
			loanDtltable.addCell(getCell("Total Recovered", 0, leftColumnHeadingFont));
			loanDtltable
					.addCell(getCell(String.valueOf(loanAccountDtls.getTotalCollection()), 0, leftColumnHeadingFont));
			PdfPTable loanDtlstable = createPdfTable(3, new float[] { 20, 10, 10 }, 1);
			loanDtlstable.addCell(getCell("Collection Details", 3, tableHeadingFont));
			loanDtlstable.addCell(getCell("Sr.No.", 0, tableColHeadingFont));
			loanDtlstable.addCell(getCell("Amount", 0, tableColHeadingFont));
			loanDtlstable.addCell(getCell("Date", 0, tableColHeadingFont));
			PdfWriter.getInstance(document, out);
			if (loanAccountDtls.getLoanCollections() != null && loanAccountDtls.getLoanCollections().size() > 0) {
				AtomicInteger srNo = new AtomicInteger(1);
				loanAccountDtls.getLoanCollections().stream().forEach(data -> {
					loanDtlstable.addCell(getCell(String.valueOf(srNo.getAndIncrement()), 0, leftColumnHeadingFont));
					loanDtlstable.addCell(getCell(String.valueOf(data.getPayment()), 0, leftColumnHeadingFont));
					loanDtlstable.addCell(getCell(String.valueOf(data.getPaymentDate()), 0, leftColumnHeadingFont));
				});
			}
			document.open();
			document.add(loanDtltable);
			Paragraph lineSpace = new Paragraph(32);
			lineSpace.setSpacingBefore(10);
			lineSpace.setSpacingAfter(10);
			document.add(lineSpace);
			document.add(loanDtlstable);
			document.close();

		} catch (DocumentException ex) {

		}

		return new ByteArrayInputStream(out.toByteArray());
	}

	private ByteArrayInputStream generateCustomerFDPDF(FDAccountDto fdAccountDto) {

		Document document = new Document(PageSize.A4, 0, 0, 50, 50);
		Font tableHeadingFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16f, Font.BOLD);

		Font leftColumnHeadingFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14f, Font.NORMAL);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			PdfPTable table = createPdfTable(2, new float[] { 10, 250 }, 1);
			table.addCell(getCell("FD Receipt", 2, tableHeadingFont));
			table.addCell(getCell("Name", 0, leftColumnHeadingFont));
			table.addCell(getCell(fdAccountDto.getCustName(), 0, leftColumnHeadingFont));
			table.addCell(getCell("Account No", 0, leftColumnHeadingFont));
			table.addCell(getCell(String.valueOf(fdAccountDto.getAccountNo()), 0, leftColumnHeadingFont));
			table.addCell(getCell("Amount", 0, leftColumnHeadingFont));
			table.addCell(getCell(String.valueOf(fdAccountDto.getAmount()), 0, leftColumnHeadingFont));
			table.addCell(getCell("Interest", 0, leftColumnHeadingFont));
			table.addCell(getCell(String.valueOf(fdAccountDto.getInterest()), 0, leftColumnHeadingFont));
			table.addCell(getCell("Interest Amount", 0, leftColumnHeadingFont));
			table.addCell(getCell(String.valueOf(fdAccountDto.getInterestAmt()), 0, leftColumnHeadingFont));
			table.addCell(getCell("Start Date", 0, leftColumnHeadingFont));
			table.addCell(getCell(String.valueOf(fdAccountDto.getStartDate()), 0, leftColumnHeadingFont));
			table.addCell(getCell("End Date", 0, leftColumnHeadingFont));
			table.addCell(getCell(String.valueOf(fdAccountDto.getEndDate()), 0, leftColumnHeadingFont));
			PdfWriter.getInstance(document, out);
			document.open();
			document.add(table);
			document.add(new Paragraph("\n\n"));
			PdfPTable receipt = createPdfTable(2, new float[] { 10, 250 }, 1);
			receipt.addCell(getCell("FD Receipt", 2, tableHeadingFont));
			receipt.addCell(getCell("Name", 0, leftColumnHeadingFont));
			receipt.addCell(getCell(fdAccountDto.getCustName(), 0, leftColumnHeadingFont));
			receipt.addCell(getCell("Account No", 0, leftColumnHeadingFont));
			receipt.addCell(getCell(String.valueOf(fdAccountDto.getAccountNo()), 0, leftColumnHeadingFont));
			receipt.addCell(getCell("Amount", 0, leftColumnHeadingFont));
			receipt.addCell(getCell(String.valueOf(fdAccountDto.getAmount()), 0, leftColumnHeadingFont));
			receipt.addCell(getCell("Interest", 0, leftColumnHeadingFont));
			receipt.addCell(getCell(String.valueOf(fdAccountDto.getInterest()), 0, leftColumnHeadingFont));
			receipt.addCell(getCell("Interest Amount", 0, leftColumnHeadingFont));
			receipt.addCell(getCell(String.valueOf(fdAccountDto.getInterestAmt()), 0, leftColumnHeadingFont));
			receipt.addCell(getCell("Start Date", 0, leftColumnHeadingFont));
			receipt.addCell(getCell(String.valueOf(fdAccountDto.getStartDate()), 0, leftColumnHeadingFont));
			receipt.addCell(getCell("End Date", 0, leftColumnHeadingFont));
			receipt.addCell(getCell(String.valueOf(fdAccountDto.getEndDate()), 0, leftColumnHeadingFont));
			document.add(receipt);
			document.close();

		} catch (DocumentException ex) {

		}

		return new ByteArrayInputStream(out.toByteArray());
	}

	private ByteArrayInputStream generateLoanAccountsPdf(List<LoanRepoDto> loanRepolst) {

		Document document = new Document(PageSize.A4, 0, 0, 50, 50);
		Font tableHeadingFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14f, Font.BOLD);
		Font tableColHeadingFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12f, Font.BOLD);
		Font leftColumnHeadingFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10f, Font.NORMAL);
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			PdfPTable loanDtlstable = createPdfTable(8, new float[] { 20, 10, 10, 10, 10, 10, 10, 10 }, 1);
			loanDtlstable.addCell(getCell("Loan Accounts", 8, tableHeadingFont));
			loanDtlstable.addCell(getCell("Customer Name", 0, tableColHeadingFont));
			loanDtlstable.addCell(getCell("Account No.", 0, tableColHeadingFont));
			loanDtlstable.addCell(getCell("Principal Amt", 0, tableColHeadingFont));
			loanDtlstable.addCell(getCell("Loan Amt", 0, tableColHeadingFont));
			loanDtlstable.addCell(getCell("Collection", 0, tableColHeadingFont));
			loanDtlstable.addCell(getCell("Remaing Collection", 0, tableColHeadingFont));
			loanDtlstable.addCell(getCell("Disbursement Date", 0, tableColHeadingFont));
			loanDtlstable.addCell(getCell("Ending Date", 0, tableColHeadingFont));
			PdfWriter.getInstance(document, out);
			if (loanRepolst != null && loanRepolst.size() > 0) {
				loanRepolst.stream().forEach(data -> {
					loanDtlstable.addCell(getCell(data.getFullName(), 0, leftColumnHeadingFont));
					loanDtlstable.addCell(getCell(String.valueOf(data.getLoanAccountNo()), 0, leftColumnHeadingFont));
					loanDtlstable.addCell(getCell(String.valueOf(data.getPrincipalAmount()), 0, leftColumnHeadingFont));
					loanDtlstable.addCell(getCell(String.valueOf(data.getLoanAmt()), 0, leftColumnHeadingFont));
					loanDtlstable.addCell(
							getCell(data.getTotalCollection() == null ? "" : String.valueOf(data.getTotalCollection()),
									0, leftColumnHeadingFont));
					loanDtlstable.addCell(getCell(
							data.getRemainCollection() == null ? "" : String.valueOf(data.getRemainCollection()), 0,
							leftColumnHeadingFont));
					loanDtlstable
							.addCell(getCell(data.getPaymentDate() == null ? "" : String.valueOf(data.getPaymentDate()),
									0, leftColumnHeadingFont));
					loanDtlstable.addCell(
							getCell(data.getLoanEndigDate() == null ? "" : String.valueOf(data.getLoanEndigDate()), 0,
									leftColumnHeadingFont));

				});
			}
			document.open();
			document.add(loanDtlstable);
			document.close();

		} catch (DocumentException ex) {

		}

		return new ByteArrayInputStream(out.toByteArray());
	}

	/*
	 * private ByteArrayInputStream generatePdf(CustomerDtlsRequest customerDto,
	 * List<CustContactPeopleReq> contactPersionDtos) {
	 * 
	 * Document document = new Document(PageSize.A4, 0, 0, 25, 25); Font
	 * tableHeadingFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14f,
	 * Font.BOLD); Font leftColumnHeadingFont =
	 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 12f, Font.NORMAL);
	 * ByteArrayOutputStream out = new ByteArrayOutputStream();
	 * 
	 * try {
	 * 
	 * PdfPTable table = createPdfTable(2, new float[] { 10, 250 }, 1);
	 * table.addCell(getCell("Persional Detail", 2, tableHeadingFont));
	 * table.addCell(getCell("Cust Id", 0, leftColumnHeadingFont));
	 * table.addCell(getCell(String.valueOf(customerDto.getCustId()), 0,
	 * leftColumnHeadingFont)); table.addCell(getCell("Name", 0,
	 * leftColumnHeadingFont)); table.addCell(getCell(customerDto.getFullName(), 0,
	 * leftColumnHeadingFont)); table.addCell(getCell("Adhar No.", 0,
	 * leftColumnHeadingFont)); table.addCell(getCell(customerDto.getAdharNo(), 0,
	 * leftColumnHeadingFont)); table.addCell(getCell("PAN No.", 0,
	 * leftColumnHeadingFont)); table.addCell(getCell(customerDto.getPanNo(), 0,
	 * leftColumnHeadingFont)); table.addCell(getCell("Address Detail", 2,
	 * tableHeadingFont)); table.addCell(getCell("Address", 0,
	 * leftColumnHeadingFont));
	 * table.addCell(getCell(customerDto.getAddress().getAddress(), 0,
	 * leftColumnHeadingFont)); table.addCell(getCell("Mobile No.", 0,
	 * leftColumnHeadingFont));
	 * table.addCell(getCell(customerDto.getAddress().getPhoneNo(), 0,
	 * leftColumnHeadingFont));
	 * 
	 * PdfWriter.getInstance(document, out); document.open(); document.add(table);
	 * document.add(new Phrase("\n\n"));
	 * document.add(getParagraph("Signature                       "));
	 * document.add(new Phrase("\n")); PdfPTable contactPersionTbl =
	 * createPdfTable(2, new float[] { 50, 50 }, 1);
	 * contactPersionTbl.addCell(getCell("Witness", 2, tableHeadingFont));
	 * contactPersionTbl.addCell(getCell("Full Name", 0, leftColumnHeadingFont));
	 * contactPersionTbl.addCell(getCell("Signature", 0, leftColumnHeadingFont)); if
	 * (contactPersionDtos != null && contactPersionDtos.size() > 0) {
	 * contactPersionDtos.stream().forEach(data -> {
	 * contactPersionTbl.addCell(getCell(data.getFullName(), 0,
	 * leftColumnHeadingFont)); contactPersionTbl.addCell(getCell("", 0,
	 * leftColumnHeadingFont)); }); } document.add(contactPersionTbl);
	 * document.close();
	 * 
	 * } catch (DocumentException ex) {
	 * 
	 * }
	 * 
	 * return new ByteArrayInputStream(out.toByteArray()); }
	 */

	public PdfPTable createPdfTable(int columns, float columnWidths[], int tableType) {
		PdfPTable table = null;
		switch (tableType) {
		case 1:
			table = new PdfPTable(columns);
			table.setWidthPercentage(90);
			return table;
		default:
			break;
		}

		return null;
	}

	public PdfPCell getCell(String content, int colspan, Font tableHeadingFont) {
		PdfPCell cell = new PdfPCell(new Phrase(content, tableHeadingFont));
		cell.setPadding(5);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setVerticalAlignment(Element.ALIGN_CENTER);
		cell.setColspan(colspan);
		return cell;
	}

	public Paragraph getParagraph(String content) {
		Paragraph paragraph = new Paragraph(content);
		paragraph.setPaddingTop(90);
		paragraph.setAlignment(Element.ALIGN_RIGHT);
		return paragraph;
	}

}
