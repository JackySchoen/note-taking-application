package com.note.taking.application.business.services;

import com.note.taking.application.util.NoteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.note.taking.application.business.models.Note;
import com.note.taking.application.data.entities.NoteEntity;
import com.note.taking.application.data.repositories.NoteRepository;
import com.note.taking.application.util.InvalidNoteException;

import java.util.Optional;

@Service
public class NoteService {
    @Autowired
    private NoteRepository noteRepository;

    /**
     * Saves a new note to storage.
     * @param note the current note object.
     * @throws InvalidNoteException when a property of the current note object is invalid.
     */
    public void saveNote(Note note) throws InvalidNoteException {
        note.checkNote();
        NoteEntity noteEntity = new NoteEntity(note);
        noteRepository.save(noteEntity);
    }

    /**
     * Gets an existing note from storage by its id.
     * @param id the id of the note you want to get from storage.
     * @return the note if one is found by the provided id.
     * @throws NoteNotFoundException when no note is found by using the provided id.
     */
    public Note getNoteById(int id) throws NoteNotFoundException {
        Optional<NoteEntity> noteFromStorage = noteRepository.findById(id);
        if (noteFromStorage.isEmpty()) throw new NoteNotFoundException("note with id \"" + id + "\" could not be found");
        return new Note(noteFromStorage.get());
    }
}
