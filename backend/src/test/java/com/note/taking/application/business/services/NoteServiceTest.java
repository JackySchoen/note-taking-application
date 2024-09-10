package com.note.taking.application.business.services;

import com.note.taking.application.business.models.Note;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class NoteServiceTest {
    @Mock
    private NoteRepository noteRepositoryMock;
    @InjectMocks
    private NoteService noteService = new NoteService();

    @Nested
    @DisplayName("method saveNote tests")
    public class SaveNote {
        @Test
        @DisplayName("should not throw InvalidNoteException with valid note")
        public void withValidNote() {
            Note note = new Note("My note", "This is my note", null);
            NoteEntity noteEntity = new NoteEntity(note);
            when(noteRepositoryMock.save(noteEntity)).thenReturn(noteEntity);
            assertDoesNotThrow(() -> noteService.saveNote(note));
        }

        @Test
        @DisplayName("should throw InvalidNoteException with invalid note (empty title)")
        public void withInvalidNoteEmptyTitle() {
            Note note = new Note("", "This is my note", null);
            NoteEntity noteEntity = new NoteEntity(note);
            when(noteRepositoryMock.save(noteEntity)).thenReturn(noteEntity);
            assertThrows(InvalidNoteException.class, () -> noteService.saveNote(note));
        }

        @Test
        @DisplayName("should throw InvalidNoteException with invalid note (empty body)")
        public void withInvalidNoteEmptyBody() {
            Note note = new Note("My note", "", null);
            NoteEntity noteEntity = new NoteEntity(note);
            when(noteRepositoryMock.save(noteEntity)).thenReturn(noteEntity);
            assertThrows(InvalidNoteException.class, () -> noteService.saveNote(note));
        }
    }

    @Nested
    @DisplayName("method getNoteById tests")
    public class GetNoteById {
        @Test
        @DisplayName("should not throw NoteNotFoundException with valid id")
        public void withValidId() {
            int id = 10;
            Note note = new Note("My note", "This is my note", null);
            NoteEntity noteEntity = new NoteEntity(note);
            when(noteRepositoryMock.findById(id)).thenReturn(Optional.of(noteEntity));
            assertDoesNotThrow(() -> noteService.getNoteById(id));
        }

        @Test
        @DisplayName("should throw NoteNotFoundException when no note is found while using provided id")
        public void withInvalidId() {
            int id = 0;
            when(noteRepositoryMock.findById(id)).thenReturn(Optional.empty());
            assertThrows(NoteNotFoundException.class, () -> noteService.getNoteById(id));
        }
    }

    @Nested
    @DisplayName("method getAllNotes tests")
    public class GetAllNotes {
        @Test
        @DisplayName("should not throw NoteNotFoundException and return a list of notes when notes are present in storage")
        public void withNotesInStorage() {
            when(noteRepositoryMock.findAll()).thenReturn(getNoteEntities());
            assertDoesNotThrow(() -> noteService.getAllNotes());
        }

        /**
         * Gets a list of note entities.
         * 
         * @return a list of note entities.
         */
        private List<NoteEntity> getNoteEntities() {
            List<NoteEntity> noteEntities = new ArrayList<NoteEntity>();
            noteEntities.add(new NoteEntity(1, "My first note", "Hello ", LocalDate.now()));
            noteEntities.add(new NoteEntity(2, "My second note", "world!", LocalDate.now()));
            return noteEntities;
        }

        @Test
        @DisplayName("should throw NoteNotFoundException when no notes are present in storage")
        public void withoutNotesInStorage() {
            when(noteRepositoryMock.findAll()).thenReturn(new ArrayList<NoteEntity>());
            assertThrows(NoteNotFoundException.class, () -> noteService.getAllNotes());
        }
    }
}