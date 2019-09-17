package com.demo.registryservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.demo.registryservice.config.FeignClientConfig;
import com.demo.registryservice.dto.UserAccountDetailDTO;

@FeignClient(name = "USERACCOUNTSERVICE", configuration = FeignClientConfig.class)
public interface UserAccountServiceClient {

	@PostMapping("/useraccountservice/useraccount/user/{userId}")
	public ResponseEntity<UserAccountDetailDTO> createUserAccount(@PathVariable Long userId);
}
