package com.note.taking.application.data.entities;

import java.time.LocalDate;

import com.note.taking.application.business.models.Note;

import jakarta.persistence.*;

@Entity
@Table(name = "notes")
public class NoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String body;
    @Column(nullable = false)
    private LocalDate creationDate;

    public NoteEntity() {}
    
    public NoteEntity(Note note)  {
        this.title = note.getTitle();
        this.body = note.getBody();
        this.creationDate = note.getCreationDate();
    }
}
