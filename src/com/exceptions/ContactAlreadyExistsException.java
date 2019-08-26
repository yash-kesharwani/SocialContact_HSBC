package com.exceptions;

public class ContactAlreadyExistsException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ContactAlreadyExistsException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ContactAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ContactAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ContactAlreadyExistsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ContactAlreadyExistsException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
