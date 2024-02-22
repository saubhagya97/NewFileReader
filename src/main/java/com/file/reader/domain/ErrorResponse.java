package com.file.reader.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ErrorResponse {
	private Integer errorCode;
	private String message;
	@JsonInclude(Include.NON_NULL)
	private List<String> errors;
	@JsonInclude(Include.NON_NULL)
	private String errCode;

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public ErrorResponse(Integer i, String errCode, String message) {
		this.errorCode = i;
		this.message = message;
		this.errCode = errCode;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public ErrorResponse(Integer errorCode, String errCode, String message, List<String> errors) {
		this.errorCode = errorCode;
		this.message = message;
		this.errors = errors;
		this.errCode = errCode;
	}

	@Override
	public String toString() {
		return "{\"errorCode\":\"" + errorCode + "\", \"message\":\"" + message + "\"}";
	}

}
