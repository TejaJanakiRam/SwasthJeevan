package com.example.backend.mapper;

import com.example.backend.dto.UserDTO;
import com.example.backend.entity.User;

public class UserMapper {
    public static User mapToUser(UserDTO userDTO){
        User user = new User(userDTO.getId(), userDTO.getUsername(), userDTO.getPassword(), userDTO.getType(),userDTO.getEmail(),userDTO.getPhone(), userDTO.getName());
        return(user);
    }

    public static UserDTO mapToUserDTO(User user){
        UserDTO userDTO = new UserDTO(user.getId(), user.getUsername(), user.getPassword(), user.getType(),user.getEmail(), user.getPhone(), user.getName());
        return(userDTO);
    }
}
