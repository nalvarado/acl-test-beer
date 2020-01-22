package com.proyect.beer.exception;

import java.util.List;

public class ErrorResponse {

	public ErrorResponse() {
		super();
	}

	public ErrorResponse(int status, String message, List<String> details) {
		super();
		this.status = status;
		this.message = message;
		this.details = details;
	}

	// General error code about nature of error
	private int status;

	// General error message about nature of error
	private String message;

	// Specific errors in API request processing
	private List<String> details;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
