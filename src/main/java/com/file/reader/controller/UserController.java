package com.file.reader.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.file.reader.domain.UserResponse;
import com.file.reader.exception.InvalidRequestException;
import com.file.reader.service.UserService;

@RestController
@RequestMapping("/file")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@PostMapping(value = "/upload")
	public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file)
			throws InvalidRequestException, Exception {
		logger.info("Uploading file data start{}", file);
		UserResponse fileResponse = userService.uploadFile(file);
		logger.info("Uploading file data end", fileResponse);
		return new ResponseEntity<>(fileResponse, HttpStatus.OK);
	}

}
