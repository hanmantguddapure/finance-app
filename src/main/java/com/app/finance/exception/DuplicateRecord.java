package com.app.finance.exception;

public class DuplicateRecord extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DuplicateRecord(String msg) {
		super(msg);
	}

}
