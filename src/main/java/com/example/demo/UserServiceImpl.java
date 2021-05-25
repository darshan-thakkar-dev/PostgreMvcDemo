package com.example.demo;

import com.example.demo.util.CycleDependencyResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	@Override
	public UserDTO login(String username, String password) {
		return userMapper.domainToDto(userRepository.findByUsernameAndPasswordAndUserType(username, password, "Doctor"), new CycleDependencyResolver());
	}

	@Override
	public UserDTO save(UserDTO user) {
		User userDomain = userMapper.dtoToDomain(user, new CycleDependencyResolver());
		return userMapper.domainToDto(userRepository.save(userDomain), new CycleDependencyResolver());
	}

	@Override
	public List<UserDTO> getAllPatients() {
		return userRepository.findAllByUserTypeOrderByIdAsc("Patient")
				.stream()
				.map(domain -> userMapper.domainToDto(domain, new CycleDependencyResolver()))
				.collect(Collectors.toList());
	}

	@Override
	public UserDTO getUserByID(Long id) {
		return userMapper.domainToDto(userRepository.findById(id).orElseThrow(NoSuchElementException::new), new CycleDependencyResolver());
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public UserDTO isUsernameAlreadyExists(String username) {
		return userMapper.domainToDto(userRepository.findByUsername(username), new CycleDependencyResolver());
	}

}
