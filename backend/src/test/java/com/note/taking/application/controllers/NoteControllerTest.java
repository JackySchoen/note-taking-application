package com.note.taking.application.controllers;

import com.note.taking.application.business.models.Note;
import com.note.taking.application.business.services.NoteService;
import com.note.taking.application.data.entities.NoteEntity;
import com.note.taking.application.data.repositories.NoteRepository;
import com.note.taking.application.util.InvalidNoteException;
import com.note.taking.application.util.NoteNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class NoteControllerTest {
    @Mock
    private NoteService noteServiceMock;
    @Mock
    private NoteRepository noteRepositoryMock;
    @InjectMocks
    private NoteController noteController;

    @Nested
    @DisplayName("method saveNote tests")
    public class SaveNote {
        @Test
        @DisplayName("should return ResponseEntity with status OK when provided with valid note")
        public void withValidNote() {
            Note note = new Note("My note", "This is my note", null);
            ResponseEntity<String> response = noteController.saveNote(note);
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }

        @Test
        @DisplayName("should return ResponseEntity with status BAD REQUEST when provided with invalid note (empty title)")
        public void withInvalidNoteTitle() {
            Note note = new Note("", "This is my note", null);
            doThrow(InvalidNoteException.class).when(noteServiceMock).saveNote(note);
            verify(noteRepositoryMock, times(0)).save(any(NoteEntity.class));
            ResponseEntity<String> response = noteController.saveNote(note);
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        }

        @Test
        @DisplayName("should return ResponseEntity with status BAD REQUEST when provided with invalid note (empty body)")
        public void withInvalidNoteBody() {
            Note note = new Note("My note", "", null);
            doThrow(InvalidNoteException.class).when(noteServiceMock).saveNote(note);
            verify(noteRepositoryMock, times(0)).save(any(NoteEntity.class));
            ResponseEntity<String> response = noteController.saveNote(note);
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        }
    }

    @Nested
    @DisplayName("method getNoteById tests")
    public class GetNoteById {
        @Test
        @DisplayName("should return ResponseEntity with status OK when provided with valid id")
        public void withValidId() {
            int id = 1;
            Note note = new Note("My note", "This is my note", LocalDate.now());
            when(noteServiceMock.getNoteById(id)).thenReturn(note);
            ResponseEntity<?> response = noteController.getNoteById(id);
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }

        @Test
        @DisplayName("should return ResponseEntity with status NOT FOUND when provided with invalid id")
        public void withInvalidId() {
            int id = 0;
            doThrow(NoteNotFoundException.class).when(noteServiceMock).getNoteById(id);
            ResponseEntity<?> response = noteController.getNoteById(id);
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        }
    }
}