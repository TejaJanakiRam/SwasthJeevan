package com.backend.backend.service.impl;

import org.springframework.stereotype.Service;

import com.backend.backend.dto.UserDto;
import com.backend.backend.entity.User;
import com.backend.backend.mapper.UserMapper;
import com.backend.backend.repository.UserRepository;
import com.backend.backend.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    @Override
    public UserDto createUser(UserDto userDto){

        User user = UserMapper.mapToUser(userDto);
        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserDto(savedUser);
    }
    
}
