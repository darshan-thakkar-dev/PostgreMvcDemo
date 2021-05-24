package com.example.demo.user;

import com.example.demo.util.CycleDependencyResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private UserMapper userMapper;

	@Override
	public UserDTO login(String username, String password) {
		return userMapper.domainToDto(userRepository.findByUsernameAndPassword(username, password), new CycleDependencyResolver());
	}

	@Override
	public UserDTO save(User user) {
		return userMapper.domainToDto(userRepository.save(user), new CycleDependencyResolver());
	}

}
