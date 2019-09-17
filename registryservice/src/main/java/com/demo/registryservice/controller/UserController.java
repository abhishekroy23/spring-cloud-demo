package com.demo.registryservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.registryservice.dto.UserDTO;
import com.demo.registryservice.service.UserService;

@RestController
@RequestMapping(path="/user") 
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping()
	public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO){
		userDTO = userService.registerUser(userDTO);
		return new ResponseEntity<UserDTO>(userDTO,HttpStatus.OK);
	}
}
