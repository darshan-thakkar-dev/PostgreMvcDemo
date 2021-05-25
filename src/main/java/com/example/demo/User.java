package com.example.demo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "person")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "user_type")
	private String userType;

	/* Patient properties */
	@Column(name = "age")
	private String age;
	@Column(name = "mobile")
	private String mobile;
	@Column(name = "address")
	private String address;
	@Column(name = "blood_group")
	private String bloodGroup;
	@Column(name = "full_name")
	private String fullName;
}
