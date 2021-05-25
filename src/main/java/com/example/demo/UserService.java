package com.example.demo;

import java.util.List;

public interface UserService {

	UserDTO login(String username, String password);

	UserDTO save(UserDTO user);

	List<UserDTO> getAllPatients();

	UserDTO getUserByID(Long id);

	void deleteUser(Long id);

	UserDTO isUsernameAlreadyExists(String username);
}
