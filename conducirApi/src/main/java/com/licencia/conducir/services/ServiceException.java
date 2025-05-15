package com.licencia.conducir.services;

public class ServiceException extends Exception {
	private static final long serialVersionUID = -7074739369995040547L;

	public ServiceException() {
		
	}

	public ServiceException(String message) {
		super(message);
	}
	
	public ServiceException(Throwable cause) {
		super(cause);
	}
	
	public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
