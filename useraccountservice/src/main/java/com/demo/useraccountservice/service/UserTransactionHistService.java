package com.demo.useraccountservice.service;

import java.util.List;

import com.demo.useraccountservice.entity.UserTransactionHist;

public interface UserTransactionHistService {

	List<UserTransactionHist> createUserTransactionHist(List<UserTransactionHist> lstUserTransactionHist);
}
