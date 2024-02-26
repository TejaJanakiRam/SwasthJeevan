package com.example.backend.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.config.JwtService;
import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.UserService;

@Service
public class UserSeriviceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Override
    public User findUserByJwtToken(String jwt) throws Exception {

        String username = jwtService.extractUsername(jwt);
        User user = userRepository.findByUsername(username);
        return(user);

    }

    @Override
    public User findUserByUsername(String username) throws Exception {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new Exception("User not found");
        }
        return (user);
    }

}
