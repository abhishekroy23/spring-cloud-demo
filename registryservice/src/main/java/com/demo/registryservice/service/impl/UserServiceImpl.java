package com.demo.registryservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.demo.registryservice.dto.UserDTO;
import com.demo.registryservice.entity.User;
import com.demo.registryservice.feign.UserAccountServiceClient;
import com.demo.registryservice.mapper.UserMapper;
import com.demo.registryservice.repository.UserRepository;
import com.demo.registryservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserMapper userMapper;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	UserAccountServiceClient userAccountServiceClient;
	
	@Override
	public UserDTO registerUser(UserDTO userDTO) {
		User user = userMapper.mapToUserEntity(userDTO);
		user = userRepository.save(user);
		//createUserAccountRestTemplateCall(user.getId());	
		userAccountServiceClient.createUserAccount(user.getId());
		
		return userMapper.mapToUserDTO(user);
	}
	
	private void createUserAccountRestTemplateCall(Long userId) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		String uri = "http://localhost:8095/useraccountservice/useraccount/user/"+userId;
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		ResponseEntity<String> response = restTemplate.postForEntity(uri, request, String.class);
		logger.info("register user:::::{}",response.getBody());
	}
}
