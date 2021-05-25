package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsernameAndPasswordAndUserType(String username, String password, String userType);

	List<User> findAllByUserTypeOrderByIdAsc(String userType);

	User findByUsername(String username);

}
