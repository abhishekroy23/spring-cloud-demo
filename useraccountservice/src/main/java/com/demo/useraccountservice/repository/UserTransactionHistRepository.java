package com.demo.useraccountservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.useraccountservice.entity.UserTransactionHist;

public interface UserTransactionHistRepository extends JpaRepository<UserTransactionHist, Long>{

}
