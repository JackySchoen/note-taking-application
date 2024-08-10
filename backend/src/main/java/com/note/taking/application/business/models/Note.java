package com.note.taking.application.business.models;

import com.note.taking.application.util.InvalidNoteException;

public class Note {
    private String title;
    private String body;

    public Note(String title, String body) {
        this.title = title;
        this.body = body;
    }

    /**
     * Checks if the current note has a valid title and body.
     * @throws InvalidNoteException when either the notes title or body is invalid.
     */
    public void checkNote() throws InvalidNoteException {
        String errorMessage = "note title or body cannot be empty";
        if (title.isEmpty() || body.isEmpty()) {
            throw new InvalidNoteException(errorMessage);
        }
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
}