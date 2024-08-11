package com.note.taking.application.business.models;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.note.taking.application.util.InvalidNoteException;

public class NoteTest {
    @Nested
    @DisplayName("method checkNote tests")
    public class CheckNote {
        @Test
        @DisplayName("should not throw with valid note")
        public void withValidNote() {
            Date date = new Date();
            Note note = new Note(123, "My note", "This is my first note", date);
            assertDoesNotThrow(note::checkNote);
        }

        @Test
        @DisplayName("should throw InvalidNoteError with invalid note (invalid id)")
        public void withInvalidId() {
            Date date = new Date();
            Note note = new Note(0, "", "This is my first note", date);
            assertThrows(InvalidNoteException.class, note::checkNote);
        }

        @Test
        @DisplayName("should throw InvalidNoteError with invalid note (invalid title)")
        public void withInvalidTitle() {
            Date date = new Date();
            Note note = new Note(123, "", "This is my first note", date);
            assertThrows(InvalidNoteException.class, note::checkNote);
        }

        @Test
        @DisplayName("should throw InvalidNoteError with invalid note (invalid body)")
        public void withInvalidBody() {
            Date date = new Date();
            Note note = new Note(123, "My note", "", date);
            assertThrows(InvalidNoteException.class, note::checkNote);
        }

        @Test
        @DisplayName("should throw InvalidNoteError with invalid note (invalid date)")
        public void withInvalidDate() {
            Date date = null;
            Note note = new Note(123, "My note", "This is my first note", date);
            assertThrows(InvalidNoteException.class, note::checkNote);
        }
    }
}