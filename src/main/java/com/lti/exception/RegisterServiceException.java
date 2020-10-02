package com.lti.exception;

public class RegisterServiceException extends RuntimeException{

	
	private static final long serialVersionUID = 1L;

	public RegisterServiceException() {
		super();
	}

	public RegisterServiceException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public RegisterServiceException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public RegisterServiceException(String arg0) {
		super(arg0);
	}

	public RegisterServiceException(Throwable arg0) {
		super(arg0);
	}

	
}
