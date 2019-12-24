package com.example.cc.controller;

import com.example.cc.exception.CreateUserExeption;
import com.example.cc.exception.LoginAlreadyUsedException;
import com.example.cc.exception.UpdateUserExeption;
import com.example.cc.model.User;
import com.example.cc.repository.UserRepository;
import com.example.cc.service.UserService;
import com.example.cc.service.dto.UserDto;
import com.example.cc.service.mapper.UserMapper;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserResourceController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final UserMapper userMapper;

    public UserResourceController(UserRepository userRepository, UserService userService, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userDtos = userMapper.toUserDTOs(userService.findAllUsers());
        return ResponseEntity.ok(userDtos);
    }

    @GetMapping("/users/authorities")
    public List<String> getAuthorities() {
        return userService.getAuthorities();
    }

    @GetMapping("/users/{login}")
    public Optional<UserDto> getUser(@PathVariable String login) {
        return userService.findUser(login)
                .map(UserDto::new);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto)
            throws CreateUserExeption, LoginAlreadyUsedException {
        Optional<User> userRepo = userRepository.findByLogin(userDto.getLogin().toLowerCase());
        if (userDto.getId() != null) {
            throw new CreateUserExeption("Создоваевый юзер не может содержать поле id!");
        } else if (userRepo.isPresent() && (!userRepo.get().getLogin().equals(userDto.getLogin()))) {
            throw new LoginAlreadyUsedException("Юзер c таким логином существует!");
        } else {
            User newUser = userService.saveUser(userDto);
            return ResponseEntity.ok(newUser);
        }
    }

    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody UserDto userDto)
            throws UpdateUserExeption, LoginAlreadyUsedException {
        Optional<User> userRepo = userRepository.findById(userDto.getId());
        if (userDto.getId() == null) {
            throw new UpdateUserExeption("Обновляемый юзер должен содержать поле id!");
        } else if (userRepo.isPresent() && (!userRepo.get().getLogin().equalsIgnoreCase(userDto.getLogin()))) {
            throw new LoginAlreadyUsedException("Юзер c таким логином существует!");
        }else {
            User newUser = userService.saveUser(userDto);
            return ResponseEntity.ok(newUser);
        }
    }
}
