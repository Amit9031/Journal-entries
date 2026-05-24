package com.Amit.journalApp.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
public class EmailServiceTests {

@Autowired
    private EmailService emailService;
@Test
public void testSendMail(){
    emailService.sendEmail("amitranjan6458@gmail.com",
            "Testing mail",
            "kaise ho sirjii");
}
}
