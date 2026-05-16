package com.Amit.journalApp.service;

import com.Amit.journalApp.Controller.Entity.User;
import com.Amit.journalApp.Repository.userRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserDetailsServiceImplTests {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @MockitoBean
    private userRepository userRepository;

    @Test
    void loadUserByUsernameTest() {

        when(userRepository.findByUserName(anyString()))
                .thenReturn(Optional.of(
                        User.builder()
                                .userName("Ranjanji")
                                .password("dasdsadsadd")
                                .roles(List.of("USER"))
                                .build()
                ));

        UserDetails user =
                userDetailsServiceImpl
                        .loadUserByUsername("Ranjanji");

        Assertions.assertNotNull(user);
        Assertions.assertEquals("Ranjanji", user.getUsername());
    }
}