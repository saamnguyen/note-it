package com.noteit.noteitapi.api;

import com.noteit.noteitapi.Mapper;
import com.noteit.noteitapi.api.viewmodel.NoteBookViewModel;
import com.noteit.noteitapi.api.viewmodel.NoteViewModel;
import com.noteit.noteitapi.db.NoteBookRepository;
import com.noteit.noteitapi.model.NoteBook;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/notebooks")
@CrossOrigin
public class NoteBookController {
    private NoteBookRepository noteBookRepository;
    private Mapper mapper;

    public NoteBookController(NoteBookRepository noteBookRepository, Mapper mapper){
        this.noteBookRepository = noteBookRepository;
        this.mapper = mapper;
    }

    @GetMapping("/all")
    public List<NoteBook> all(){
        List<NoteBook> allCategories  = this.noteBookRepository.findAll();
        return allCategories;
    }

    @PostMapping
    public NoteBook save(@RequestBody NoteBookViewModel noteBookViewModel, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ValidationException();
        }
        NoteBook noteBookEntity = this.mapper.convertToNotebookEntity(noteBookViewModel);
        this.noteBookRepository.save(noteBookEntity);
        return noteBookEntity;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        this.noteBookRepository.deleteById(UUID.fromString(id));
    }
}
