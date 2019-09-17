package com.demo.useraccountservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.useraccountservice.entity.UserAccountDetail;

public interface UserAccountDetailRepository extends JpaRepository<UserAccountDetail, Long>{

	List<UserAccountDetail> findByAccountNumberIn(List<String> accountNumbers);
	Optional<UserAccountDetail> findByUserId(Long userId);
}
