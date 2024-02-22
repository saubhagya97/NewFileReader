package com.file.reader.service;

import org.springframework.web.multipart.MultipartFile;
import com.file.reader.domain.UserResponse;
import com.file.reader.exception.InvalidRequestException;

public interface UserService {

	UserResponse uploadFile(MultipartFile file) throws InvalidRequestException, Exception;

}
