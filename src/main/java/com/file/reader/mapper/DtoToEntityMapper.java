package com.file.reader.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.file.reader.dao.UserRepository;
import com.file.reader.exception.InvalidRequestException;
import com.file.reader.model.UserEntity;
import com.file.reader.utils.ErrorCodes;

@Component
public class DtoToEntityMapper {
	@Autowired
	UserRepository userRepository;

	public List<UserEntity> getListOfUserEntity(List<String[]> readUsers) throws InvalidRequestException {
		List<UserEntity> entities = new ArrayList<>();
		for (String[] user : readUsers) {
			entities.add(processUser(user));
		}
		return entities;
	}

	private UserEntity processUser(String[] user) throws InvalidRequestException {
		if (user.length == 0 || user[0].isEmpty()) {
			throw new InvalidRequestException(ErrorCodes.INVALID_INPUT, "User name is missing in the csv file");
		}
		String userName = user[0];
		UserEntity userEntity = userRepository.findByUserName(userName);
		if (Objects.isNull(userEntity)) {
			userEntity = new UserEntity();
			userEntity.setUserName(userName);
		}
		userEntity.setFirstName(getValueIfExits(user, 1));
		userEntity.setLastName(getValueIfExits(user, 2));
		userEntity.setEmail(getValueIfExits(user, 3));
		userEntity.setPhoneNumber(getValueIfExits(user, 4));
		return userEntity;
	}

	private String getValueIfExits(String[] user, int i) {

		return (user.length > 1 && !user[i].isEmpty()) ? user[i] : null;
	}

}
