package com.example.demo;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;

@Data
@ToString
public class UserDTO {

	private Long id;
	private String username;
	private String password;
	private String userType;

	private String age;
	private String mobile;
	private String address;
	private String bloodGroup;
	private String fullName;
}
