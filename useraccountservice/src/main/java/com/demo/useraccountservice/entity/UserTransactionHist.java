package com.demo.useraccountservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_transaction_hist")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTransactionHist {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "transaction_hist_id")
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "transaction_account_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)  
	private UserAccountDetail account;
	@Column(name = "transaction_acc_no")
	private String accountNumber;
	@Column(name = "transaction_type")
	private String transactionType;
	@Column(name = "transaction_balance")
	private Long balance;
	@Column(name = "transaction_amt")
	private Long transactionAmount;
}
