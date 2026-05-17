package com.Amit.journalApp.service;

import com.Amit.journalApp.Controller.Entity.User;
import com.Amit.journalApp.Repository.userRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class userService {


    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private userRepository userRepository;

    public void saveEntry(User user) {
        userRepository.save(user);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public boolean saveNewEntry(User user) {
       try{ user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
       }

       catch(Exception e){
         log.error("Error occurrred for {} :", user.getUserName(), e);
         log.warn("Amit");
           log.info("IIII");
           log.debug("DDDD");
           log.trace("TTTTT");

       }
       return true;
    }


    public void del(String id) {
        userRepository.deleteById(id);
    }

    public User updateEntry(String id, User user) {
        user.setId(id);
        return userRepository.save(user);
    }

    public Optional<User> Idfind(String id) {
        return userRepository.findById(id);
    }

    public List<User> findall() {
        return userRepository.findAll();
    }

    // ✅ FIXED METHOD
    public Optional<User> findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void createUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(user);
    }
}