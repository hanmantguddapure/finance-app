package com.app.finance.exception;

public class RecordNotFound extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public enum Errors {
		RECORD_NOT_FOUND;
	}

	public RecordNotFound(String recordNotFound) {
		super(recordNotFound);
	}

}
