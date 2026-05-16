package com.Amit.journalApp.Repository;

import com.Amit.journalApp.Controller.Entity.JournalEntry;
import com.Amit.journalApp.Controller.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userRepository extends MongoRepository<User, String> {
    Optional<User> findByUserName(String userName);
    void deleteByUserName(String userName);

}