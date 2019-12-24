package com.example.cc.controller;

import com.example.cc.model.Authority;
import com.example.cc.service.UserService;
import com.example.cc.service.dto.UserDto;
import com.example.cc.service.mapper.UserMapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserResourceController.class)
class UserResourceControllerTest {

    private static final String DEFAULT_LOGIN = "yura";
    private static final String UPDATE_LOGIN = "sergey";
    private static final String DEFAULT_PASSWORD = "password";
    private static final String UPDATE_PASSWORD = "newpassword";
    private static final String DEFAULT_EMAIL = "hi@mail.ru";
    private static final String UPDATE_EMAIL = "hello@mail.ru";
    private static final String DEFAULT_FIRST_NAME = "Yura";
    private static final String UPDATE_FIRST_NAME = "Sergey";
    private static final String DEFAULT_LAST_NAME = "Govorushkin";
    private static final String UPDATE_LAST_NAME = "Labuzov";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserMapper userMapper;

    private final String URL = "/api/users";

    private UserDto buildUserDto(Long id) {
        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setLogin(DEFAULT_LOGIN);
        userDto.setPassword(DEFAULT_PASSWORD);
        userDto.setEmail(DEFAULT_EMAIL);
        userDto.setFirstName(DEFAULT_FIRST_NAME);
        userDto.setLastName(DEFAULT_LAST_NAME);
        userDto.setAuthorities(Collections.singleton("USER"));
        return userDto;
    }

    @Test
    void testGetAllUsers() throws Exception {
        UserDto userDto = buildUserDto(1L);

        List<UserDto> userDtos = Arrays.asList(userDto);

        when(userService.findAllUsers()).thenReturn(new ArrayList<>());
        when(userMapper.toUserDTOs(any())).thenReturn(userDtos);

        mockMvc.perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].login").value(DEFAULT_LOGIN))
                .andExpect(jsonPath("$[0].password").value(DEFAULT_PASSWORD))
                .andExpect(jsonPath("$[0].email").value(DEFAULT_EMAIL))
                .andExpect(jsonPath("$[0].firstName").value(DEFAULT_FIRST_NAME))
                .andExpect(jsonPath("$[0].lastName").value(DEFAULT_LAST_NAME));
    }

    @Test
    void testGetAuthorities() throws Exception {
        Authority userAuthority = new Authority();
        userAuthority.setId(1L);
        userAuthority.setName("USER");

        Authority adminAuthority = new Authority();
        adminAuthority.setId(2L);
        adminAuthority.setName("ADMIN");

        List<String> authorityList = Arrays.asList(userAuthority, adminAuthority)
                .stream().map(Authority::getName).collect(Collectors.toList());

        when(userService.getAuthorities()).thenReturn(authorityList);

        mockMvc.perform(get(URL + "/authorities"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("USER"))
                .andExpect(jsonPath("$[1].name").value("ADMIN"));
    }

    @Test
    void testGetUser() {
//        UserDto userDto = buildUserDto(1L);
//
//        when(userService.findUser(userDto.getLogin())).thenReturn(userDto);
    }

    @Test
    void testCreateUser() {
    }

    @Test
    void testUpdateUser() {
    }
}