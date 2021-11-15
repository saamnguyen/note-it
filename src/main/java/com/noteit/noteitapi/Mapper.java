package com.noteit.noteitapi;

import com.noteit.noteitapi.api.viewmodel.NoteBookViewModel;
import com.noteit.noteitapi.api.viewmodel.NoteViewModel;
import com.noteit.noteitapi.db.NoteBookRepository;
import com.noteit.noteitapi.model.Note;
import com.noteit.noteitapi.model.NoteBook;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Mapper {
    private NoteBookRepository noteBookRepository;

    public Mapper(NoteBookRepository noteBookRepository){
        this.noteBookRepository = noteBookRepository;
    }

    public NoteViewModel convertToNoteViewModel(Note entity){
        NoteViewModel noteViewModel = new NoteViewModel();
        noteViewModel.setTitle(entity.getTitle());
        noteViewModel.setId(entity.getId().toString());
        noteViewModel.setLastModifiedOn(entity.getLastModifiedOn());
        noteViewModel.setText(entity.getText());
        noteViewModel.setNotebookId(entity.getNoteBook().getId().toString());

        return noteViewModel;
    }

    public Note convertToNoteEntity(NoteViewModel noteViewModel){
        NoteBook noteBook = this.noteBookRepository.findById(UUID.fromString(noteViewModel.getNotebookId())).get();
        Note entity = new Note(noteViewModel.getId(), noteViewModel.getTitle(), noteViewModel.getText(), noteBook);

        return entity;
    }

    public NoteBookViewModel convertToNotebookViewModel(NoteBookViewModel entity){
        NoteBookViewModel viewModel = new NoteBookViewModel();
        viewModel.setId(entity.getId().toString());
        viewModel.setName(entity.getName());
        viewModel.setNbNotes(entity.getNbNotes());

        return viewModel;
    }

    public NoteBook convertToNotebookEntity(NoteBookViewModel viewModel){
        NoteBook entity = new NoteBook(viewModel.getId(), viewModel.getName());

        return entity;
    }
}
