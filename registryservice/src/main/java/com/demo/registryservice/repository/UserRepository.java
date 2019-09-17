package com.demo.registryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.registryservice.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
