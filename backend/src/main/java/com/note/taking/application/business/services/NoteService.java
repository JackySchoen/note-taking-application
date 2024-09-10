package com.note.taking.application.business.services;

import com.note.taking.application.business.models.Note;
import com.note.taking.application.data.entities.NoteEntity;
import com.note.taking.application.data.repositories.NoteRepository;
import com.note.taking.application.util.InvalidIdException;
import com.note.taking.application.util.InvalidNoteException;
import com.note.taking.application.util.NoteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    @Autowired
    private NoteRepository noteRepository;

    /**
     * Saves a new note to storage.
     *
     * @param note the current note object.
     * @throws InvalidNoteException when a property of the current note object is
     *                              invalid.
     */
    public void saveNote(Note note) throws InvalidNoteException {
        note.checkNote();
        NoteEntity noteEntity = new NoteEntity(note);
        noteRepository.save(noteEntity);
    }

    /**
     * Gets an existing note from storage by its id.
     *
     * @param id the id of the note you want to get from storage.
     * @return the note if one is found by using the provided id.
     * @throws NoteNotFoundException when no note is found by using the provided id.
     * @throws InvalidIdException    when the id is either below 0 or above the max
     *                               integer value.
     */
    public Note getNoteById(int id) throws NoteNotFoundException, InvalidIdException {
        checkId(id);
        Optional<NoteEntity> noteFromStorage = noteRepository.findById(id);
        if (noteFromStorage.isEmpty())
            throw new NoteNotFoundException("note with id \"" + id + "\" could not be found");
        return new Note(noteFromStorage.get());
    }

    /**
     * Checks if id is either below 0, or above integer max value.
     *
     * @param id the id of the note you want to get from storage.
     * @throws InvalidIdException when id is either below 0, or above integer max
     *                            value.
     */
    private void checkId(int id) throws InvalidIdException {
        if (id < 0 || id == Integer.MAX_VALUE) {
            throw new InvalidIdException("id cannot be below 0 or above integer max value");
        }
    }

    /**
     * Gets all notes from storage.
     * 
     * @return all notes from storage as a List.
     * @throws NoteNotFoundException when no notes could be retrieved from storage.
     */
    public List<Note> getAllNotes() {
        List<NoteEntity> noteEntities = noteRepository.findAll();
        List<Note> notes = new ArrayList<Note>();
        if (noteEntities.isEmpty())
            throw new NoteNotFoundException("no notes found");
        for (NoteEntity noteEntity : noteEntities) {
            notes.add(new Note(noteEntity));
        }
        return notes;
    }
}
