package com.backend.backend.mapper;

import com.backend.backend.dto.UserDto;
import com.backend.backend.entity.User;

public class UserMapper {
    public static UserDto mapToUserDto(User user)
    {
        return new UserDto(
            user.getId(),
            user.getEmail(),
            user.getUsername()
        );
    }

    public static User mapToUser(UserDto userDto)
    {
        return new User(
            userDto.getId(),
            userDto.getEmail(),
            userDto.getUsername()
        );
    }
}
