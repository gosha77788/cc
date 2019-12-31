package com.example.cc.service;

import com.example.cc.exception.EmailAlreadyUsedException;
import com.example.cc.exception.UsernameAlreadyUsedException;
import com.example.cc.model.Authority;
import com.example.cc.model.User;
import com.example.cc.repository.AuthorityRepository;
import com.example.cc.repository.UserRepository;
import com.example.cc.security.AuthoritiesConstants;
import com.example.cc.service.dto.UserDto;
import com.example.cc.service.util.RandomUtil;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

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

    public User registrationUser(UserDto userDto, String password) {
        userRepository.findByLogin(userDto.getLogin().toLowerCase()).ifPresent(user -> {
            if (user.isActivated()) {
                throw new UsernameAlreadyUsedException();
            }
        });
        userRepository.findByEmail(userDto.getEmail()).ifPresent(user -> {
            if (user.isActivated()) {
                throw new EmailAlreadyUsedException();
            }
        });
        String encryptedPassword = passwordEncoder.encode(password);
        User newUser = new User();
        newUser.setLogin(userDto.getLogin().toLowerCase());
        newUser.setPassword(encryptedPassword);
        newUser.setFirstName(userDto.getFirstName());
        newUser.setLastName(userDto.getLastName());
        newUser.setEmail(userDto.getEmail().toLowerCase());
        newUser.setActivated(false);
        newUser.setActivationKey(RandomUtil.generateActivationKey());
        Set<Authority> authorities = new HashSet<>();
        authorityRepository.findByName(AuthoritiesConstants.USER).ifPresent(authorities::add);
        newUser.setAuthorities(authorities);
        userRepository.save(newUser);
        return newUser;
    }

    public Optional<User> activateRegistration(@RequestParam(value = "key") String key) {
        return userRepository.findByActivationKey(key).map(user -> {
            user.setActivationKey(null);
            user.setActivated(true);
            return user;
        });
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public List<String> getAuthorities() {
        return authorityRepository.findAll()
                .stream()
                .map(Authority::getName)
                .collect(Collectors.toList());
    }

    public Optional<User> findUser(String login) {
        return userRepository.findByLogin(login);
    }
}
