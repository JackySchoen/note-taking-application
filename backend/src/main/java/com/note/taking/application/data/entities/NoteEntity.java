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

    public NoteEntity(int id, String title, String body, LocalDate creationDate) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.creationDate = creationDate;
    }

    /**
     * Gets the id of the note entity.
     * @return the id of the note entity.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the title of the note entity.
     * @return the title of the note entity.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the body of the note entity.
     * @return the body of the note entity.
     */
    public String getBody() {
        return body;
    }

    /**
     * Gets the creation date of the note entity.
     * @return the creation date of the note entity.
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }
}
