package com.note.taking.application.data.entities;

import java.util.Date;

import com.note.taking.application.business.models.Note;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "notes")
public class NoteEntity {
    @Id
    private int id;
    private String title;
    private String body;
    private Date creationDate;

    public NoteEntity() {}
    
    public NoteEntity(Note note)  {
        this.id = note.getId();
        this.title = note.getTitle();
        this.body = note.getBody();
        this.creationDate = note.getCreationDate();
    }
}
