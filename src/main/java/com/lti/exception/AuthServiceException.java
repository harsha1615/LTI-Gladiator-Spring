package com.lti.exception;

public class AuthServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AuthServiceException() {
		super();
	}

	public AuthServiceException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public AuthServiceException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public AuthServiceException(String arg0) {
		super(arg0);
	}

	public AuthServiceException(Throwable arg0) {
		super(arg0);
	}

}
