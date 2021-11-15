package com.noteit.noteitapi.db;

import com.noteit.noteitapi.model.Note;
import com.noteit.noteitapi.model.NoteBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository {
    List<Note> findAllByNoteBook(NoteBook noteBook);
}
