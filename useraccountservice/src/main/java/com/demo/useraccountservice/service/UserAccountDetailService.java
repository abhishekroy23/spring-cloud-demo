package com.demo.useraccountservice.service;

import com.demo.useraccountservice.dto.UserAccountDetailDTO;

public interface UserAccountDetailService {

	UserAccountDetailDTO createUserAccount(UserAccountDetailDTO userAccountDetailDTO);
	UserAccountDetailDTO createUserAccount(Long userId);
	String fundsTransfer(String fromAccount,String toAccount,Long amount);
	UserAccountDetailDTO getUserAccount(Long userId);
}
