package com.Amit.journalApp.Controller.Entity;


import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;
import java.time.LocalDateTime;
import java.util.Date;

@Document(collection="journal_entries")
@Data
@NoArgsConstructor
public class JournalEntry {
    @Id
    private String id;

    private String content;
    @NonNull
    private String title;
    private LocalDateTime date;
}