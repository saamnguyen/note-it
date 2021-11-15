package com.noteit.noteitapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NoteBook {
    @Id
    private UUID id;
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "noteBook", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Note> notes;


    protected NoteBook() {
        this.id = UUID.randomUUID();
        this.notes = new ArrayList<>();
    }

    public NoteBook(String name) {
        this();
        this.name = name;
    }

    public NoteBook(String id, String name) {
        this();
        if (id != null) {
            this.id = UUID.fromString(id);
        }
        this.name = name;
    }


    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public int getNbOfNotes() {
        return this.notes.size();
    }
}
