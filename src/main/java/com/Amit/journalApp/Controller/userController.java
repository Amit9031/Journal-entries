package com.Amit.journalApp.Controller;

import com.Amit.journalApp.Controller.Entity.JournalEntry;
import com.Amit.journalApp.Controller.Entity.User;
import com.Amit.journalApp.Repository.userRepository;
import com.Amit.journalApp.service.JornalEntryService;
import com.Amit.journalApp.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class userController {
@Autowired
private userService userService;
@Autowired
userRepository userRepository;


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
          String userName=  securityContext.getAuthentication().getName();
        User existingUser = userService.findByUserName(userName)
                .orElseThrow(() -> new RuntimeException("User not found: " + userName));

        existingUser.setUserName(user.getUserName());
        existingUser.setPassword(user.getPassword());

        userService.saveEntry(existingUser);

        return ResponseEntity.ok(existingUser);
    }
    @DeleteMapping
    public ResponseEntity<?> deletbyuserid() {

        SecurityContext securityContext = SecurityContextHolder.getContext();

        userRepository.deleteByUserName(
                securityContext.getAuthentication().getName()
        );

        return ResponseEntity.ok("User deleted");
    }


}
