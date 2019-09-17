package com.demo.useraccountservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_account_detail")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountDetail {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "user_acc_id")
	private Long id;
	@Column(name = "user_acc_no")
	private String accountNumber;	
	@Column(name = "user_acc_balance")
	private Long balance;
	@Column(name = "user_id")
	private Long userId;
}
