package com.example.cc.service.mapper.impl;

import com.example.cc.model.Authority;
import com.example.cc.model.User;
import com.example.cc.service.dto.UserDto;
import com.example.cc.service.mapper.UserMapper;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class UserMapperImpl implements UserMapper {

    public List<UserDto> toUserDTOs(List<User> users) {
        return users.stream()
                .filter(Objects::nonNull)
                .map(this::toUserDto)
                .collect(Collectors.toList());
    }

    public UserDto toUserDto(User user) {
        return new UserDto(user);
    }

    public User toUser(UserDto userDto) {
        if (userDto == null) {
            return null;
        } else {
            User user = new User();
            user.setId(userDto.getId());
            user.setLogin(userDto.getLogin());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setEmail(userDto.getEmail());
            user.setActivated(userDto.isActivated());
            Set<Authority> authorities = this.authoritiesFromStrings(userDto.getAuthorities());
            user.setAuthorities(authorities);
            return user;
        }
    }


    private Set<Authority> authoritiesFromStrings(Set<String> authoritiesAsString) {
        Set<Authority> authorities = new HashSet<>();
        if (authoritiesAsString != null) {
            authorities = authoritiesAsString.stream().map(string -> {
                Authority auth = new Authority();
                auth.setName(string);
                return auth;
            }).collect(Collectors.toSet());
        }
        return authorities;
    }
}
