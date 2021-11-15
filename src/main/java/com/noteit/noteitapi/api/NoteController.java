package com.noteit.noteitapi.api;

import com.noteit.noteitapi.Mapper;
import com.noteit.noteitapi.api.viewmodel.NoteViewModel;
import com.noteit.noteitapi.db.NoteBookRepository;
import com.noteit.noteitapi.db.NoteRepository;
import com.noteit.noteitapi.model.Note;
import com.noteit.noteitapi.model.NoteBook;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.List;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin
public class NoteController {
    private NoteRepository noteRepository;
    private NoteBookRepository noteBookRepository;
    private Mapper mapper;

    public NoteController(NoteRepository noteRepository, NoteBookRepository noteBookRepository, Mapper mapper){
        this.noteRepository = noteRepository;
        this.noteBookRepository = noteBookRepository;
        this.mapper = mapper;
    }

    @GetMapping("/all")
    public List<NoteViewModel> all(){
        List<Note> notes = this.noteRepository.findAll();
        List<NoteViewModel> notesViewModel = notes.stream().map(note -> this.mapper.convertToNoteViewModel(note))
                .collect(Collectors.toList());

        return notesViewModel;
    }

    @GetMapping("/byId/{id}")
    public NoteViewModel byId(@PathVariable String id) {
        Note note = (Note) this.noteRepository.findById(UUID.fromString(id)).orElse(null);

        if (note == null) {
            throw new EntityNotFoundException();
        }

        NoteViewModel noteViewModel = this.mapper.convertToNoteViewModel(note);

        return noteViewModel;
    }
//
//    @GetMapping("/byNotebook/{notebookId}")
//    public List<NoteViewModel> byNotebook(@PathVariable String notebookId) {
//        List<Note> notes = new ArrayList<>();
//
//        var notebook = this.noteBookRepository.findById(UUID.fromString(notebookId));
//        if (notebook.isPresent()) {
//            notes = this.noteRepository.findAllByNotebook(notebook.get());
//        }
//
//        // map to note view model
//        var notesViewModel = notes.stream()
//                .map(note -> this.mapper.convertToNoteViewModel(note))
//                .collect(Collectors.toList());
//
//        return notesViewModel;
//    }
//
//    @PostMapping
//    public Note save(@RequestBody NoteViewModel noteCreateViewModel, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            throw new ValidationException();
//        }
//
//        var noteEntity = this.mapper.convertToNoteEntity(noteCreateViewModel);
//
//        // save note instance to db
//        this.noteRepository.save(noteEntity);
//
//        return noteEntity;
//    }
//
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable String id) {
//        this.noteRepository.deleteById(UUID.fromString(id));
//    }
}
