package com.note.taking.application.util;

public class InvalidNoteException extends RuntimeException {
    public InvalidNoteException(String message) {
        super(message);
    }
}
