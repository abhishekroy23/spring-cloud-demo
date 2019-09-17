package com.demo.useraccountservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.useraccountservice.entity.UserTransactionHist;
import com.demo.useraccountservice.repository.UserTransactionHistRepository;
import com.demo.useraccountservice.service.UserTransactionHistService;

@Service
@Transactional
public class UserTransactionHistServiceImpl implements UserTransactionHistService{
	
	@Autowired
	UserTransactionHistRepository userTransactionHistRepository;

	@Override
	public List<UserTransactionHist> createUserTransactionHist(List<UserTransactionHist> lstUserTransactionHist) {
		lstUserTransactionHist = userTransactionHistRepository.saveAll(lstUserTransactionHist);
		return lstUserTransactionHist;
	}

}
