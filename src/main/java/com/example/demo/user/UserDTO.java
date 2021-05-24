package com.example.demo.user;

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
