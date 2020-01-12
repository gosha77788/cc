package com.example.cc.service.dto;

import java.util.Set;
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

    private String firstName;
    private String lastName;
    private boolean activated = false;
    private Set<String> authorities;
}
