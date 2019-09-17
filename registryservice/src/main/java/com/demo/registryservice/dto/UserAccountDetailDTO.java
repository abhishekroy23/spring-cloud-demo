package com.demo.registryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountDetailDTO {

	private Long id;
	private String accountNumber;	
	private Long balance;
	private Long userId;
	
}
