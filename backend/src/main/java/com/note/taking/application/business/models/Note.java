package com.note.taking.application.business.models;

import java.time.LocalDate;

import com.note.taking.application.util.InvalidNoteException;

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

    /**
     * Checks if the current note has a valid title, body and date.
     * @throws InvalidNoteException when either the notes title, body or date is invalid.
     */
    public void checkNote() throws InvalidNoteException {
        if (title.isEmpty() || body.isEmpty()) {
            throw new InvalidNoteException("note title or body cannot be empty");
        }
    }

    /**
     * Checks if creationDae is null, if it is, sets the creationDate property to a new LocalDate.
     * @param creationDate the current creation date in yyyy-mm-dd format.
     */
    public void setCreationDate(LocalDate creationDate) {
        if (creationDate == null) this.creationDate = LocalDate.now();
        else this.creationDate = creationDate;
    }

    /**
     * Gets the title of the note.
     * @return the title of the note.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the body of the note.
     * @return the body of the note.
     */
    public String getBody() {
        return body;
    }

    /**
     * Gets the creation date of the note.
     * @return the creation date of the note.
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }
}