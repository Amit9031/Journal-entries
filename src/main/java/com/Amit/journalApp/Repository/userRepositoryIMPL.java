package com.Amit.journalApp.Repository;

import com.Amit.journalApp.Controller.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class userRepositoryIMPL {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getUserForsA() {

        Query query = new Query();

        query.addCriteria(
                Criteria.where("email").exists(true)
                        .and("sentimentAnalysis").is("Ranjanji")
        );

        return mongoTemplate.find(query, User.class);
    }
    }
