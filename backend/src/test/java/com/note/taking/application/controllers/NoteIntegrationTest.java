package com.note.taking.application.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.note.taking.application.business.models.Note;
import com.note.taking.application.data.entities.NoteEntity;
import com.note.taking.application.data.repositories.NoteRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
public class NoteIntegrationTest {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public static void setUp(@Autowired NoteRepository noteRepository) {
        NoteEntity noteEntity = new NoteEntity(1, "Reminder", "Water the plants", LocalDate.now());
        noteRepository.save(noteEntity);
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Nested
    @DisplayName("POST note route integration tests")
    public class PostRouteTests {
        @Test
        @DisplayName("should save new note, return status OK and success message")
        public void withValidNote() throws Exception {
            Note note = new Note("My note", "This is my note", null);
            String noteAsJSON = objectMapper.writeValueAsString(note);
            mockMvc.perform(MockMvcRequestBuilders.post("/notes")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(noteAsJSON))
                    .andExpect(status().isOk())
                    .andExpect(content().string("note saved successfully"));
        }

        @Test
        @DisplayName("should not save new note with invalid title, should return status BAD REQUEST")
        public void withInvalidNoteEmptyTitle() throws Exception {
            Note note = new Note("", "This is my note", null);
            String noteAsJSON = objectMapper.writeValueAsString(note);
            mockMvc.perform(MockMvcRequestBuilders.post("/notes")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(noteAsJSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string("note title or body cannot be empty"));
        }

        @Test
        @DisplayName("should not save new note with invalid body, should return status BAD REQUEST")
        public void withInvalidNoteEmptyBody() throws Exception {
            Note note = new Note("My note", "", null);
            String noteAsJSON = objectMapper.writeValueAsString(note);
            mockMvc.perform(MockMvcRequestBuilders.post("/notes")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(noteAsJSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string("note title or body cannot be empty"));
        }
    }

    @Nested
    @DisplayName("GET note route integration tests")
    public class GetRouteTests {
        @Test
        @DisplayName("should get note from storage when valid id (1) is provided")
        public void withValidId() throws Exception {
            int id = 1;
            mockMvc.perform(MockMvcRequestBuilders.get("/notes/" + id))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.title").value("Reminder"));
        }

        @Test
        @DisplayName("should not get note from storage when invalid id (0) is provided")
        public void withInvalidIdZero() throws Exception {
            int id = 0;
            mockMvc.perform(MockMvcRequestBuilders.get("/notes/" + id))
                    .andExpect(status().isNotFound())
                    .andExpect(content().string(containsString("could not be found")));
        }

        @Test
        @DisplayName("should not get note from storage when invalid id (2) is provided")
        public void withInvalidIdTwo() throws Exception {
            int id = 2;
            mockMvc.perform(MockMvcRequestBuilders.get("/notes/" + id))
                    .andExpect(status().isNotFound())
                    .andExpect(content().string(containsString("could not be found")));
        }
    }
}
