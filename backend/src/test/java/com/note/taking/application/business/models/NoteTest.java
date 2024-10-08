package com.note.taking.application.business.models;

import com.note.taking.application.util.InvalidNoteException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class NoteTest {
    @Nested
    @DisplayName("method checkNote tests")
    public class CheckNote {
        @Test
        @DisplayName("should not throw with valid note")
        public void withValidNote() {
            LocalDate date = LocalDate.now();
            Note note = new Note("My note", "This is my first note", date);
            assertDoesNotThrow(note::checkNote);
        }

        @Test
        @DisplayName("should throw InvalidNoteException with invalid note (invalid title)")
        public void withInvalidTitle() {
            LocalDate date = LocalDate.now();
            Note note = new Note("", "This is my first note", date);
            assertThrows(InvalidNoteException.class, note::checkNote);
        }

        @Test
        @DisplayName("should throw InvalidNoteException with invalid note (invalid body)")
        public void withInvalidBody() {
            LocalDate date = LocalDate.now();
            Note note = new Note("My note", "", date);
            assertThrows(InvalidNoteException.class, note::checkNote);
        }
    }

    @Nested
    @DisplayName("method setCreationDate tests")
    public class SetCreationDate {
        @Test
        @DisplayName("should set creationDate property when provided date is LocalDate")
        public void withLocalDate() {
            LocalDate date = LocalDate.now();
            Note note = new Note("My note", "This is my first note", date);
            assertNotNull(note.getCreationDate());
        }

        @Test
        @DisplayName("should set creationDate property to a new date when provided date is null")
        public void withDateNull() {
            LocalDate date = null;
            Note note = new Note("My note", "This is my first note", date);
            assertNotNull(note.getCreationDate());
        }
    }
}