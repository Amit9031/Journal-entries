package com.Amit.journalApp.cache;


import com.Amit.journalApp.Controller.Entity.configJournalAppEntity;
import com.Amit.journalApp.Repository.configJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    @Autowired
    private configJournalAppRepository configJournalAppRepository;

    private Map<String, String> APPCACHE;


@PostConstruct
    public void init(){
    APPCACHE = new HashMap<>();
    List<configJournalAppEntity> all = configJournalAppRepository.findAll();
      for(configJournalAppEntity app : all){
          APPCACHE.put(app.getKey(),app.getValue());

      }
    }
}
