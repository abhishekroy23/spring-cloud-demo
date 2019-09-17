package com.demo.useraccountservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FundsTransferRequestDTO {

	private String fromAcc;
	private String toAcc;
	private Long transferAmount;
}
