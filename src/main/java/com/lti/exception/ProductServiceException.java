package com.lti.exception;

public class ProductServiceException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ProductServiceException() {
		super();
	}

	public ProductServiceException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public ProductServiceException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ProductServiceException(String arg0) {
		super(arg0);
	}

	public ProductServiceException(Throwable arg0) {
		super(arg0);
	}
	
}
