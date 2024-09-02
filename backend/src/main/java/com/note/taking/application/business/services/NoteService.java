package com.note.taking.application.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.note.taking.application.business.models.Note;
import com.note.taking.application.data.entities.NoteEntity;
import com.note.taking.application.data.repositories.NoteRepository;
import com.note.taking.application.util.InvalidNoteException;

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
}
