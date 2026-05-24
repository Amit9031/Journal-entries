package com.Amit.journalApp.scheduler;

import com.Amit.journalApp.Controller.Entity.JournalEntry;
import com.Amit.journalApp.Controller.Entity.User;
import com.Amit.journalApp.Repository.userRepository;
import com.Amit.journalApp.Repository.userRepositoryIMPL;
import com.Amit.journalApp.cache.AppCache;
import com.Amit.journalApp.service.EmailService;
import com.Amit.journalApp.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private userRepositoryIMPL userRepositoryIMPL;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;


    @Autowired
    private AppCache appCache;
@Scheduled(cron = "0 0 9 * * SUN")
    public void fetchloudaAndsendMail() {

        List<User> users = userRepositoryIMPL.getUserForsA();

        for (User user : users) {
        List<JournalEntry> journalEntries = user.getJournalEntries();
            List<String> filteredEntries = journalEntries
                    .stream()
                    .filter(x -> x.getDate()
                            .isAfter(LocalDateTime.now()
                                    .minus(7, ChronoUnit.DAYS)))
                    .map(x-> x.getContent())
                    .collect(Collectors.toList());

                    String joinentrybabu=String.join(",", filteredEntries);
                String sentiment=sentimentAnalysisService.getSentiment(joinentrybabu);
                emailService.sendEmail(user.getEmail(), "Sentiment last 7 days", "Yahi h beta senti bodddyyy" );
        }
    }
    @Scheduled(cron = "0 0/10 * ? * *")
        public void clearAppCacheBeta(){
            appCache.init();
        }
}
