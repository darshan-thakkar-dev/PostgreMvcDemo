package com.example.demo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserDTO {

	private Long id;
	private String username;
	private String password;
	private String userType;
}
