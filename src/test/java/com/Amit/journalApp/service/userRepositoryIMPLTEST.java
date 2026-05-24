package com.Amit.journalApp.service;

import com.Amit.journalApp.Controller.Entity.User;
import com.Amit.journalApp.Repository.userRepository;
import com.Amit.journalApp.Repository.userRepositoryIMPL;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class userRepositoryIMPLTEST {

@Autowired
public userRepositoryIMPL userRepositories;

    @Test
    public void testSaveNewUser(){
        Assertions.assertNotNull(userRepositories.getUserForsA());
    }
}