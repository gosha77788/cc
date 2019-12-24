package com.example.cc.service.mapper;

import com.example.cc.model.User;
import com.example.cc.service.dto.UserDto;
import java.util.List;

public interface UserMapper {

    List<UserDto> toUserDTOs(List<User> users);

    UserDto toUserDto(User user);

    User toUser(UserDto userDto);
    }
