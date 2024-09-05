package com.note.taking.application.business.models;

import com.note.taking.application.data.entities.NoteEntity;
import com.note.taking.application.util.InvalidNoteException;

import java.time.LocalDate;

public class Note {
    private int id;
    private String title;
    private String body;
    private LocalDate creationDate;

    public Note(String title, String body, LocalDate creationDate) {
        this.title = title;
        this.body = body;
        setCreationDate(creationDate);
    }

    public Note(NoteEntity noteEntity) {
        this.id = noteEntity.getId();
        this.title = noteEntity.getTitle();
        this.body = noteEntity.getBody();
        this.creationDate = noteEntity.getCreationDate();
    }

    /**
     * Checks if the current note has a valid title and body.
     *
     * @throws InvalidNoteException when either the notes title or body is invalid.
     */
    public void checkNote() throws InvalidNoteException {
        if (title.isEmpty() || body.isEmpty()) {
            throw new InvalidNoteException("note title or body cannot be empty");
        }
    }

    /**
     * Gets the title of the note.
     *
     * @return the title of the note.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the body of the note.
     *
     * @return the body of the note.
     */
    public String getBody() {
        return body;
    }

    /**
     * Gets the creation date of the note.
     *
     * @return the creation date of the note.
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * Checks if creationDate is null. If it is, sets the creationDate property to new date. Otherwise, sets creationDate property to provided creationDate argument.
     *
     * @param creationDate the current creation date in yyyy-mm-dd format.
     */
    public void setCreationDate(LocalDate creationDate) {
        if (creationDate == null) this.creationDate = LocalDate.now();
        else this.creationDate = creationDate;
    }
}