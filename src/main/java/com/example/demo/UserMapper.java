package com.example.demo;

import com.example.demo.util.IBaseMapper;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper extends IBaseMapper<UserDTO, User> {}
