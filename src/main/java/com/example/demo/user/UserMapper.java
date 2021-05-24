package com.example.demo.user;

import com.example.demo.util.IBaseMapper;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper extends IBaseMapper<UserDTO, User> {}
