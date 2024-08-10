package com.note.taking.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.note.taking.application.business.models.Note;
import com.note.taking.application.business.services.NoteService;

@RestController()
@RequestMapping("/notes")
public class NoteController {
    @Autowired
    private NoteService noteService;

    /**
     * Saves a new note to storage.
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
}
