package com.example.cc.service.dto;

import com.example.cc.model.Authority;
import com.example.cc.model.User;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDto {

    private Long id;

    @NotBlank
    private String login;

    @Email
    private String email;

    private String password;
    private String firstName;
    private String lastName;
    private boolean activated = true;
    private Set<String> authorities;

    public UserDto() {
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.activated = user.isActivated();
        this.authorities = user.getAuthorities().stream()
                .map(Authority::getName)
                .collect(Collectors.toSet());
    }
}
