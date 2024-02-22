package com.file.reader.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.file.reader.exception.InvalidRequestException;

public interface FileReaderService {

	List<String[]> readFile(MultipartFile file) throws InvalidRequestException, Exception;

	String fileType();

}
