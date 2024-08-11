package com.note.taking.application.business.models;

import java.util.Date;
import java.util.Random;

import com.note.taking.application.util.InvalidNoteException;

public class Note {
    private int id;
    private String title;
    private String body;
    private Date creationDate;

    public Note(int id, String title, String body, Date creationDate) {
        if (id == 0) {
            this.id = generateId();
        } else { 
            this.id = id;
        }
        this.title = title;
        this.body = body;
        this.creationDate = creationDate;
    }

    // TODO improve randomness of id generation
    // TODO check if I want recursion
    /**
     * Generates a random id. After generation, checks if generated id is not null. If generated id is null, generates new id.
     * @return a random id.
     */
    private int generateId() {
        Random random = new Random();
        int randomId = random.nextInt(5000);
        if (randomId == 0) {
            generateId();
        }
        return randomId;
    }

    /**
     * Checks if the current note has a valid title, body and date.
     * @throws InvalidNoteException when either the notes title, body or date is invalid.
     */
    public void checkNote() throws InvalidNoteException {
        if (title.isEmpty() || body.isEmpty()) {
            throw new InvalidNoteException("note title or body cannot be empty");
        }
        if (creationDate == null) {
            throw new InvalidNoteException("date cannot be null");
        }
    }

    /**
     * Gets the id of the note.
     * @return the id of the note.
     */
    public int getId() {
        return id;
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
    public Date getCreationDate() {
        return creationDate;
    }
}