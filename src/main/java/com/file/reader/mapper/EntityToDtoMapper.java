package com.file.reader.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.file.reader.domain.UserFileResponse;
import com.file.reader.model.UserEntity;
@Component
public class EntityToDtoMapper {
	ModelMapper modelMapper = new ModelMapper();

	public UserFileResponse toResponse(UserEntity userEntity) {
		return modelMapper.map(userEntity, UserFileResponse.class);

	}

}
