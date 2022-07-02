package com.app.finance.exception;

public class BadRequest  extends RuntimeException{
	public enum Errors {
		BAD_REQUEST;
	}

	public BadRequest(String msg) {
		super(msg);
	}
}
