package com.file.reader.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.file.reader.domain.ErrorResponse;
import com.file.reader.utils.ErrorCodes;

public class CustomGlobalException extends ResponseEntityExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(CustomGlobalException.class);

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> errors = ex.getBindingResult().getAllErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());

		logger.error("Error in request input - {}", errors);
		ErrorResponse errorResponse = new ErrorResponse(ErrorCodes.INVALID_INPUT.getErrorCode(), null,
				ErrorCodes.INVALID_INPUT.getErrorMessage(), errors);
		return new ResponseEntity<>(errorResponse, status);
	}

	@ExceptionHandler(HttpClientErrorException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ResponseEntity<ErrorResponse> handleHttpClientErrorException(HttpClientErrorException ex,
			WebRequest request) {

		logger.error("Error in service - {}", ex);
		logger.error("Error in service getRawStatusCode- {}", ex.getRawStatusCode());
		return buildErrorResponse(ex, ErrorCodes.INVALID_INPUT, null, HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(HttpServerErrorException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	protected ResponseEntity<ErrorResponse> handleHttpServerErrorException(HttpServerErrorException ex,
			WebRequest request) {

		logger.error("Error in service - {}", ex);
		return buildErrorResponse(ex, ErrorCodes.EXTERNAL_API_EXCEPTION, null, HttpStatus.INTERNAL_SERVER_ERROR,
				request);
	}

	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	protected ResponseEntity<ErrorResponse> handleAllUncaughtException(RuntimeException ex, WebRequest request) {

		logger.error("Unknown Error in service - {}", ex);
		return buildErrorResponse(ex, ErrorCodes.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	@ExceptionHandler(InvalidRequestException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	protected ResponseEntity<ErrorResponse> handleInvalidRequestException(InvalidRequestException ex,
			WebRequest request) {

		logger.error("Error in request input - {}", ex);
		return buildErrorResponse(ex, ex.getErrorCode(), ex.getError(), ex.getErrorMessage(), ex.getErrors(),
				HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(FileProcessingException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	protected ResponseEntity<ErrorResponse> handleInvalidRequestException(FileProcessingException ex,
			WebRequest request) {

		logger.error("Error in request input - {}", ex);
		return buildErrorResponse(ex, ex.getErrorCode(), ex.getError(), ex.getErrorMessage(), ex.getErrors(),
				HttpStatus.BAD_REQUEST, request);
	}

	private ResponseEntity<ErrorResponse> buildErrorResponse(Exception exception, ErrorCodes errorCodes,
			List<String> errors, HttpStatus httpStatus, WebRequest request) {
		return buildErrorResponse(exception, errorCodes.getErrorCode(), errorCodes.getErrorMessage(), errors,
				httpStatus, request);
	}

	private ResponseEntity<ErrorResponse> buildErrorResponse(Exception exception, Integer code, String message,
			List<String> errors, HttpStatus httpStatus, WebRequest request) {
		return buildErrorResponse(exception, code, null, message, errors, httpStatus, request);
	}

	private ResponseEntity<ErrorResponse> buildErrorResponse(Exception exception, Integer code, String errCode,
			String message, List<String> errors, HttpStatus httpStatus, WebRequest request) {
		ErrorResponse errorResponse = errors != null ? new ErrorResponse(code, errCode, message, errors)
				: new ErrorResponse(code, errCode, message);

		return ResponseEntity.status(httpStatus).body(errorResponse);
	}
}
