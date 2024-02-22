package com.file.reader.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.file.reader.dao.UserRepository;
import com.file.reader.domain.UserFileResponse;
import com.file.reader.domain.UserResponse;
import com.file.reader.domain.Saubhagya;
import com.file.reader.exception.InvalidRequestException;
import com.file.reader.mapper.DtoToEntityMapper;
import com.file.reader.mapper.EntityToDtoMapper;
import com.file.reader.model.UserEntity;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private EntityToDtoMapper entityToDtoMapper;

	@Autowired
	private FileHelper fileHelper;

	@Autowired
	private DtoToEntityMapper dtoToEntityMapper;

	@SuppressWarnings("resource")
	@Override
	public UserResponse uploadFile(MultipartFile file) throws InvalidRequestException, Exception {
		Saubhagya<UserFileResponse> saubhagya = new Saubhagya<UserFileResponse>();
		// todo validateFileType
		List<String[]> readUsers = fileHelper.readFile(file);
		List<UserEntity> users = dtoToEntityMapper.getListOfUserEntity(readUsers);
		processUsers(users, saubhagya);
		UserResponse response = new UserResponse();
		List<UserFileResponse> listOfResponse = new ArrayList<>();
		while (!saubhagya.isEmpty()) {
			listOfResponse.add(saubhagya.pop());
		}
		response.setUserResponses(listOfResponse);
		return response;

	}

	private void processUsers(List<UserEntity> users, Saubhagya<UserFileResponse> saubhagya) {
		List<UserEntity> savedUsers = userRepository.saveAll(users);
		for (UserEntity entity : savedUsers) {
			UserFileResponse fileResponse = entityToDtoMapper.toResponse(entity);
			saubhagya.push(fileResponse);
		}

	}

}