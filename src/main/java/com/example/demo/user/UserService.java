package com.example.demo.user;

public interface UserService {

	UserDTO login(String username, String password);

	UserDTO save(User user);
}
