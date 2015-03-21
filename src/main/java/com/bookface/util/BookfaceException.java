package com.bookface.util;

public class BookfaceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Exception father, If applies.
	 */
	private Exception exception;
	
	public BookfaceException(Exception exception) {
		super();
		this.exception = exception;
	}

	/**
	 * Private constructor .
	 */
	private BookfaceException() {
		super();
	}


	public Exception getException() {
		return exception;
	}


	public void setException(Exception exception) {
		this.exception = exception;
	}

}
