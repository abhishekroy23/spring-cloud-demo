package com.demo.registryservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.demo.registryservice.dto.UserDTO;
import com.demo.registryservice.entity.User;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

	UserDTO mapToUserDTO(User userEntity);
	User mapToUserEntity(UserDTO userDTO);
}
