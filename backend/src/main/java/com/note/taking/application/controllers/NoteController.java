package com.note.taking.application.controllers;

import com.note.taking.application.business.models.Note;
import com.note.taking.application.business.services.NoteService;
import com.note.taking.application.util.InvalidIdException;
import com.note.taking.application.util.NoteNotFoundException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// TODO look into adding @ControllerAdvice

@RestController()
@RequestMapping("/notes")
public class NoteController {
    @Autowired
    private NoteService noteService;

    /**
     * Saves a new note to storage.
     *
     * @param note the newly created note.
     * @return a ResponseEntity with a fitting response status and message.
     */
    @PostMapping
    public ResponseEntity<String> saveNote(@RequestBody Note note) {
        try {
            noteService.saveNote(note);
            return ResponseEntity.ok().body("note saved successfully");
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    /**
     * Gets an existing note from storage by its id.
     *
     * @param id the id of the note you want to get from storage.
     * @return a ResponseEntity with a fitting response status and message.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getNoteById(@PathVariable int id) {
        try {
            Note note = noteService.getNoteById(id);
            return ResponseEntity.ok().body(note);
        } catch (NoteNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        } catch (InvalidIdException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Gets all notes from storage.
     * 
     * @return all notes from storage.
     */
    @GetMapping
    public ResponseEntity<?> getAllNotes() {
        try {
            List<Note> notes = noteService.getAllNotes();
            return ResponseEntity.ok().body(notes);
        } catch (NoteNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
