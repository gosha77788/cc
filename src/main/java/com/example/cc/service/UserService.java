package com.example.cc.service;

import com.example.cc.model.Authority;
import com.example.cc.model.User;
import com.example.cc.repository.AuthorityRepository;
import com.example.cc.repository.UserRepository;
import com.example.cc.service.dto.UserDto;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setLogin(userDto.getLogin().toLowerCase());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail().toLowerCase());
        String encryptedPassword = passwordEncoder.encode(userDto.getPassword());
        user.setPassword(encryptedPassword);
        user.setActivated(true);
        if (userDto.getAuthorities() != null) {
            Set<Authority> authorities = userDto.getAuthorities().stream()
                    .map(authorityRepository::findByName)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet());
            user.setAuthorities(authorities);
        }
        userRepository.save(user);
        return user;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public List<String> getAuthorities() {
        return authorityRepository.findAll().stream().map(Authority::getName).collect(Collectors.toList());
    }

    public Optional<User> findUser(String login) {
        return userRepository.findByLogin(login);
    }
}
