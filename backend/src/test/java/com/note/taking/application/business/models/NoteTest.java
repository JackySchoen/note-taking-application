package com.note.taking.application.business.models;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

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
            LocalDate date = LocalDate.now();
            Note note = new Note("My note", "This is my first note", date);
            assertDoesNotThrow(note::checkNote);
        }

        @Test
        @DisplayName("should throw InvalidNoteError with invalid note (invalid id)")
        public void withInvalidId() {
            LocalDate date = LocalDate.now();
            Note note = new Note("", "This is my first note", date);
            assertThrows(InvalidNoteException.class, note::checkNote);
        }

        @Test
        @DisplayName("should throw InvalidNoteError with invalid note (invalid title)")
        public void withInvalidTitle() {
            LocalDate date = LocalDate.now();
            Note note = new Note("", "This is my first note", date);
            assertThrows(InvalidNoteException.class, note::checkNote);
        }

        @Test
        @DisplayName("should throw InvalidNoteError with invalid note (invalid body)")
        public void withInvalidBody() {
            LocalDate date = LocalDate.now();
            Note note = new Note("My note", "", date);
            assertThrows(InvalidNoteException.class, note::checkNote);
        }

        @Test
        @DisplayName("should throw InvalidNoteError with invalid note (invalid date)")
        public void withInvalidDate() {
            LocalDate date = null;
            Note note = new Note("My note", "This is my first note", date);
            assertDoesNotThrow(note::checkNote);
        }
    }
}