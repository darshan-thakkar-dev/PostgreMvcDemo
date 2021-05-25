package com.example.demo;

import com.example.demo.util.CycleDependencyResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDTO login(String username, String password) {
        return userMapper.domainToDto(userRepository.findByUsernameAndPassword(username, password), new CycleDependencyResolver());
    }

    @Override
    public UserDTO save(User user) {
        return userMapper.domainToDto(userRepository.save(user), new CycleDependencyResolver());
    }

    @Override
    public List<UserDTO> getAllPatients() {
        return userRepository.findAllByUserType("Patient").stream().map(
                domain -> userMapper.domainToDto(domain, new CycleDependencyResolver())).collect(Collectors.toList());
    }

}
