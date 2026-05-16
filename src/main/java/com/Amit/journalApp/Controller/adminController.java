package com.Amit.journalApp.Controller;


import com.Amit.journalApp.Controller.Entity.User;
import com.Amit.journalApp.Repository.userRepository;
import com.Amit.journalApp.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")

public class adminController {

    @Autowired
    public userService userService;

    @GetMapping("/all-users")
    public ResponseEntity<?> getUsers() {
      List<User>all= userService.getAll();
      if(all!=null && !all.isEmpty()){
          return new ResponseEntity<>(all,HttpStatus.OK);
    }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-admin")
    public void createAdmin(@RequestBody User user) {
        userService.createUser(user);
    }


}
