package org.crowdguru.service.exception;

public class InvalidAccountTypeException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidAccountTypeException(String message) {
		super(message);
	}

	public InvalidAccountTypeException(Throwable cause) {
		super(cause);
	}

	public InvalidAccountTypeException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidAccountTypeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}