package com.example.demo;

import java.util.List;

public interface UserService {

	UserDTO login(String username, String password);

	UserDTO save(User user);

	List<UserDTO> getAllPatients();
}
