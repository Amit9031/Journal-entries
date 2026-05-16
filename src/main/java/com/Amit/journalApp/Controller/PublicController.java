package com.Amit.journalApp.Controller;

import com.Amit.journalApp.Controller.Entity.User;
import com.Amit.journalApp.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private userService userService;


    @PostMapping("/createUser")
    public boolean createUser(@RequestBody User newUser){
        userService.saveNewEntry(newUser);

        return true;
    }

    @GetMapping("/health-check")
    public String healthCheck() {
        return "ok";
    }
}
