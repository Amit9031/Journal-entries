package com.Amit.journalApp.Controller;

import com.Amit.journalApp.Controller.Entity.JournalEntry;
import com.Amit.journalApp.Controller.Entity.User;
import com.Amit.journalApp.Repository.JournalEntryRepository;
import com.Amit.journalApp.service.JornalEntryService;
import com.Amit.journalApp.service.userService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {
    @Autowired
    private JornalEntryService jornalEntryService;
 @Autowired
 private userService userService;

 @Autowired JournalEntryRepository journalEntryRepository;

    @GetMapping
    public ResponseEntity<?> getAllJournalEntriesOfUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String userName=  securityContext.getAuthentication().getName();



        User user = userService.findByUserName(userName).orElse(null);

        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<JournalEntry> all = user.getJournalEntries();

        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @PostMapping
    public ResponseEntity<JournalEntry> addsomthings(@RequestBody JournalEntry journalEntry) {
     try{
         SecurityContext securityContext = SecurityContextHolder.getContext();
         String userName=  securityContext.getAuthentication().getName();

         jornalEntryService.addthings(journalEntry,userName);
         return new ResponseEntity<JournalEntry>(journalEntry,HttpStatus.CREATED);

     }
     catch(Exception e){
         return new ResponseEntity<JournalEntry>(HttpStatus.BAD_REQUEST);

     }
    }





    @GetMapping("id/{myID}")
    public ResponseEntity<JournalEntry> getJournalId(@PathVariable String myID) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String userName=  securityContext.getAuthentication().getName();
        User user = userService.findByUserName(userName).orElse(null);
        List<JournalEntry> all = user.getJournalEntries()
                .stream()
                .filter(x -> x.getId().equals(myID))
                .collect(Collectors.toList());
        if(all != null && !all.isEmpty()){
        Optional<JournalEntry> idfindkaro = jornalEntryService.Idfind(myID);
       if(idfindkaro.isPresent()){
           return new ResponseEntity<JournalEntry>(idfindkaro.get(), HttpStatus.OK);
       }}
        return new ResponseEntity<JournalEntry>( HttpStatus.NOT_FOUND);

    }
    @DeleteMapping("id/{myID}")
    public ResponseEntity<?> deleteEntryById(
            @PathVariable String myID) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String userName=  securityContext.getAuthentication().getName();


        jornalEntryService.del(myID, userName);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


@PutMapping("id/{myID}")
public ResponseEntity<?> updateJournalById( @RequestBody JournalEntry newEntry, @PathVariable String myID) {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    String userName=  securityContext.getAuthentication().getName();

    User user= userService.findByUserName(userName).orElse(null);
    if(user==null){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    JournalEntry updateEntry = jornalEntryService.updateEntry(myID,newEntry);

    return new ResponseEntity<>(updateEntry, HttpStatus.OK);
    }
}
