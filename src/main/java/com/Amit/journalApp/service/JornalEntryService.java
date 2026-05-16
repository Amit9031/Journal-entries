package com.Amit.journalApp.service;

import com.Amit.journalApp.Controller.Entity.JournalEntry;
import com.Amit.journalApp.Controller.Entity.User;
import com.Amit.journalApp.Repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service

public class JornalEntryService {
@Autowired
    private JournalEntryRepository journalEntryRepository;
@Autowired
private userService userService;



public  void saveEntry(JournalEntry entry){

    journalEntryRepository.save(entry);
}
    @Transactional
    public void addthings(JournalEntry journalEntry, String userName) {

        try {

            User user = userService.findByUserName(userName).orElse(null);
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);

            userService.saveUser(user);

        } catch (Exception e) {

            System.out.println(e);

            throw new RuntimeException(
                    "An error occurred while saving the entry.", e);
        }}

public void   del(String id, String userName){
     User user=userService.findByUserName(userName).orElseThrow(() -> new RuntimeException("User not found: " + userName));
    boolean removed= user.getJournalEntries().removeIf(x->x.getId().equals(id));
  if(removed){
    userService.saveUser(user);
    journalEntryRepository.deleteById(id);}
}
    public JournalEntry updateEntry(String id, JournalEntry entry){
        JournalEntry old= journalEntryRepository.findById(id).orElse(null);
        if(old!=null){
            old.setContent(entry.getContent());
            old.setTitle(entry.getTitle());
            return journalEntryRepository.save(old);

        }
        return null;
    }
public Optional<JournalEntry> Idfind(String id){
    return journalEntryRepository.findById(id);
}
public  List<JournalEntry>findall(){
    return journalEntryRepository.findAll();
}
}
