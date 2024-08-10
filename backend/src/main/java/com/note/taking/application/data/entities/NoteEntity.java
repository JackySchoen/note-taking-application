package com.note.taking.application.data.entities;

import com.note.taking.application.business.models.Note;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "notes")
public class NoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String body;

    public NoteEntity() {}
    
    public NoteEntity(Note note)  {
        this.title = note.getTitle();
        this.body = note.getBody();
    }
}
