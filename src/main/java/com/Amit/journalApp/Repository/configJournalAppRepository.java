package com.Amit.journalApp.Repository;

import com.Amit.journalApp.Controller.Entity.configJournalAppEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface configJournalAppRepository
        extends MongoRepository<configJournalAppEntity, String> {

}