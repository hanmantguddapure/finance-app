package com.app.finance.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomeExceptionHandler extends ResponseEntityExceptionHandler {
	private static final Logger logger = LogManager.getLogger(CustomeExceptionHandler.class);

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionDetails exceptionDetails = new ExceptionDetails(ex.getMessage(), request.getDescription(false));
		logger.error("Exception Occured ", ex);
		return new ResponseEntity<>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(RecordNotFound.class)
	final ResponseEntity<?>recordNotFoundException(RecordNotFound recordExce,WebRequest request){
		logger.error("Record Not Found Exception ",recordExce);
		ExceptionDetails exceptioDetails=new ExceptionDetails(recordExce.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(exceptioDetails,HttpStatus.NO_CONTENT);
	}
	@ExceptionHandler(NullPointerException.class)
	final ResponseEntity<?>nullPointerException(NullPointerException recordExce,WebRequest request){
		logger.error("Null Pointer Exception ",recordExce);
		ExceptionDetails exceptioDetails=new ExceptionDetails(recordExce.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(exceptioDetails,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(DuplicateKeyException.class)
	final ResponseEntity<?>duplicateKeyException(DuplicateKeyException recordExce,WebRequest request){
		logger.error("Null Pointer Exception ",recordExce);
		ExceptionDetails exceptioDetails=new ExceptionDetails(recordExce.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(exceptioDetails,HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(DuplicateRecord.class)
	final ResponseEntity<?>duplicateRecord(DuplicateKeyException recordExce,WebRequest request){
		logger.error("Null Pointer Exception ",recordExce);
		ExceptionDetails exceptioDetails=new ExceptionDetails(recordExce.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(exceptioDetails,HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(Exception.class)
	final ResponseEntity<?> uncoughtException(Exception ex, WebRequest request) {
		ExceptionDetails exceptionDetails = new ExceptionDetails(ex.getMessage(), request.getDescription(false));
		logger.error("Exception Occured ", ex);
		return new ResponseEntity<>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
