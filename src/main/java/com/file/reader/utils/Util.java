package com.file.reader.utils;

import org.springframework.web.multipart.MultipartFile;

import com.file.reader.exception.InvalidRequestException;

public class Util {

	public static String findFileType(MultipartFile file) throws InvalidRequestException {
		String contentType = file.getContentType();

		if (contentType != null
				&& (contentType.equals(UserConstants.CSV_FILE) || contentType.equals(UserConstants.EXCEL_FILE))) {
			return contentType;
		} else {
			throw new InvalidRequestException(ErrorCodes.INVALID_INPUT, "Invalid file format");
		}
	}

}
