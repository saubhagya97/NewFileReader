package com.file.reader.utils;

public enum ErrorCodes {
	SUCCESS(0, "Success"), ERROR(100, "Error"), INVALID_INPUT(400, "Invalid Input"),
	DATA_NOT_FOUND(401, "Details Not Available"), RESOURCE_NOT_FOUND(404, "Requested Resource not found"),
	EXTERNAL_API_EXCEPTION(997, "Error fetching data from external api"),
	DB_ERROR(998, "Error saving data in the database"), UNKNOWN_ERROR(999, "Error calling user service Service"),
	FILE_PROCESSING_ERROR(1001, "Error processing the uploaded file");

	private Integer errorCode;
	private String errorMessage;

	ErrorCodes(Integer errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
