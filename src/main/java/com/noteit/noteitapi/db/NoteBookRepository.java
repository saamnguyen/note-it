package com.noteit.noteitapi.db;

import com.noteit.noteitapi.model.NoteBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NoteBookRepository extends JpaRepository<NoteBook, UUID> {
}
