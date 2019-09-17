package com.demo.useraccountservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.useraccountservice.dto.FundsTransferRequestDTO;
import com.demo.useraccountservice.dto.UserAccountDetailDTO;
import com.demo.useraccountservice.service.UserAccountDetailService;

@RestController
@RequestMapping("/useraccount")
public class UserAccountController {
	
	@Autowired
	UserAccountDetailService userAccountDetailService;

	@PostMapping("/user/{userId}")
	public ResponseEntity<UserAccountDetailDTO> createUserAccount(@PathVariable Long userId){
		
		UserAccountDetailDTO userAccountDetailDTO = userAccountDetailService.createUserAccount(userId);
		return new ResponseEntity<>(userAccountDetailDTO, HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<UserAccountDetailDTO> createUserAccount(@RequestBody UserAccountDetailDTO userAccountDetailDTO){
		
		userAccountDetailDTO = userAccountDetailService.createUserAccount(userAccountDetailDTO);
		return new ResponseEntity<>(userAccountDetailDTO, HttpStatus.OK);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<UserAccountDetailDTO> getUserAccount(@PathVariable Long userId){
		
		UserAccountDetailDTO userAccountDetailDTO = userAccountDetailService.getUserAccount(userId);
		return new ResponseEntity<>(userAccountDetailDTO, HttpStatus.OK);
	}
	
	@PostMapping("/fundtransfer")
	public ResponseEntity<String> fundsTransfer(@RequestBody FundsTransferRequestDTO fundsTransferRequestDTO) {

		String response = userAccountDetailService.fundsTransfer(fundsTransferRequestDTO.getFromAcc(),
				fundsTransferRequestDTO.getToAcc(), fundsTransferRequestDTO.getTransferAmount());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
