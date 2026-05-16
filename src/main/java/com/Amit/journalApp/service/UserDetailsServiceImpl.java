package com.Amit.journalApp.service;

import com.Amit.journalApp.Repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private userRepository userRepository;
    @Override

    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        com.Amit.journalApp.Controller.Entity.User user =
                userRepository.findByUserName(username).orElse(null);

        if (user != null) {

            return org.springframework.security.core.userdetails.User
                    .builder()
                    .username(user.getUserName())
                    .password(user.getPassword())
                    .roles(user.getRoles().toArray(new String[0]))
                    .build();
        }

        throw new UsernameNotFoundException("User not found");
    }
}
