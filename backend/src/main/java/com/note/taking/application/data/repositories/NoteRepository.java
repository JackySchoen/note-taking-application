package com.note.taking.application.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.note.taking.application.data.entities.NoteEntity;

@Repository
public interface NoteRepository extends JpaRepository<NoteEntity, Integer> {
}