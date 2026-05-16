package com.Amit.journalApp.service;

import com.Amit.journalApp.Controller.Entity.User;
import com.Amit.journalApp.Repository.userRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class userServiceTests {

    @Autowired
    private userRepository userRepository;

    @Autowired
    private userService userService;


    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testFindByUsername(User  name) {
        assertTrue(userService.saveNewEntry(name));



    }
    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,3,5",
            "5,2,10"
    })
    public void test(int a, int b, int expected) {
        assertEquals(expected, a + b);
    }
}
