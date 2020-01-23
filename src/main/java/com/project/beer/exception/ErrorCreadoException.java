package com.project.beer.exception;

public class ErrorCreadoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ErrorCreadoException(String msg) {
		super(msg);
	}
	
	public ErrorCreadoException(String msg, Throwable e) {
		super(msg, e);
	}

}
