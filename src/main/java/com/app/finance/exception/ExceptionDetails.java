package com.app.finance.exception;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ExceptionDetails implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1974680811999922687L;
	private LocalDateTime timestamp;
	private String message;
	private String details;

	public ExceptionDetails( String message, String details) {
		this.timestamp = LocalDateTime.now();
		this.message = message;
		this.details = details;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

}
