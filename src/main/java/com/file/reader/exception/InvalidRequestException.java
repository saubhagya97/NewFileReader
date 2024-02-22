package com.file.reader.exception;

import java.util.ArrayList;
import java.util.List;

import com.file.reader.utils.ErrorCodes;

public class InvalidRequestException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Integer errorCode;
	String errorMessage;
	String error;
	List<String> errors = new ArrayList<String>();

	public InvalidRequestException() {
		super();
	}

	public InvalidRequestException(ErrorCodes error) {
		super(error.getErrorMessage());
		this.errorCode = error.getErrorCode();
		this.errorMessage = error.getErrorMessage();
		this.errors.add(error.getErrorMessage());
	}

	public InvalidRequestException(ErrorCodes error, String message) {
		super(error.getErrorMessage());
		this.errorCode = error.getErrorCode();
		this.errorMessage = error.getErrorMessage();
		this.errors.add(message);
	}

	public InvalidRequestException(ErrorCodes error, String err, String message) {
		super(error.getErrorMessage());
		this.errorCode = error.getErrorCode();
		this.errorMessage = error.getErrorMessage();
		this.error = err;
		this.errors.add(message);
	}

	public InvalidRequestException(Integer errorCode, String errorMessage, String error) {
		super(errorMessage);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.errors.add(error);
	}

	public InvalidRequestException(Integer errorCode, String errorMessage, String error, Throwable cause) {
		super(errorMessage, cause);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.errors.add(error);
	}

	public InvalidRequestException(ErrorCodes error, List<String> errors) {
		super(error.getErrorMessage());
		this.errorCode = error.getErrorCode();
		this.errorMessage = error.getErrorMessage();
		this.errors = errors;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
