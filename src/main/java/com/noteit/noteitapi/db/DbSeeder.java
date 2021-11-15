package com.noteit.noteitapi.db;

import com.noteit.noteitapi.model.Note;
import com.noteit.noteitapi.model.NoteBook;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "noteit.db.recreate", havingValue = "true")
public class DbSeeder implements CommandLineRunner {
    private NoteBookRepository noteBookRepository;
    private NoteRepository noteRepository;

    public DbSeeder(NoteBookRepository noteBookRepository, NoteRepository noteRepository){
        this.noteBookRepository = noteBookRepository;
        this.noteRepository = noteRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //Remove all existing entities
        this.noteBookRepository.deleteAll();
        this.noteRepository.deleteAll();

        //Save default Notebook
        NoteBook defaultNoteBook = new NoteBook("Default");
        this.noteBookRepository.save(defaultNoteBook);

        NoteBook quoteNoteBook = new NoteBook("Quote");
        this.noteBookRepository.save(quoteNoteBook);

        //Save default Note
        Note note = new Note("Hello", "Welcome Note IT", defaultNoteBook);
        this.noteRepository.save(note);

        //save quote note
        Note quoteNote = new Note("Latin quote", "carpe diem", quoteNoteBook);
        this.noteRepository.save(quoteNote);

        System.out.println("Initialized database");
    }
}
