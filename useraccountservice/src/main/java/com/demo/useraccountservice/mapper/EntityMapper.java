package com.demo.useraccountservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.demo.useraccountservice.dto.UserAccountDetailDTO;
import com.demo.useraccountservice.entity.UserAccountDetail;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EntityMapper {

	UserAccountDetailDTO mapToUserAccountDTO(UserAccountDetail userEntity);
	UserAccountDetail mapToUserAccountEntity(UserAccountDetailDTO userDTO);
}
