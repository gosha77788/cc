package com.example.cc.service;

import com.example.cc.model.Role;
import com.example.cc.model.Status;
import com.example.cc.model.User;
import com.example.cc.repository.RoleRepository;
import com.example.cc.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;

    }

    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        roles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(roles);
        user.setStatus(Status.ACTIVE);

        User registeredUser = userRepository.save(user);
        return registeredUser;
    }

    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public User findByUsername(String name) {
        User user = userRepository.findByUsername(name);
        return user;
    }


}
