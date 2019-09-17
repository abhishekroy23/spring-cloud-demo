package com.demo.registryservice.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user_reg")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class User {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "user_id")    
    private Long id;
	
	@Column(name = "user_first_name" )
    private String firstName;
	
	@Column(name = "user_last_name" )
    private String lastName;
	
	@Column(name = "user_gender")
    private String gender;
	
	@Column(name = "user_pan" )
    private String panDetail;
	
	@Column(name = "user_phone" )	
    private String phoneNo;
	
	@Column(name = "user_email")
    private String email;
		
	@Column(name = "user_dob" )
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
}
