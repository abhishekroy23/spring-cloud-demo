package com.demo.registryservice.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	private Long id;

	private String firstName;

	private String lastName;

	private String gender;

	private String panDetail;

	private String phoneNo;

	private String email;

	private Date dateOfBirth;
}
