package com.example.tacos.monitor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Component
@Endpoint(id = "notes", enableByDefault = true)
public class NotesEndpoint {

    private List<Note> notes;

    public NotesEndpoint()
    {
        notes = new ArrayList<>();
    }
    
    @ReadOperation
    public List<Note> getNotes()
    {
        return notes;
    }

    @WriteOperation
    public List<Note> addNote(String text)
    {
        notes.add(new Note(text));

        return notes;
    }

    @DeleteOperation
    public List<Note> removeNote(int index)
    {
        if (index < notes.size())
            notes.remove(index);

        return notes;
    }
}

@RequiredArgsConstructor
class Note
{
    @Getter
    private Date time = new Date();

    @Getter
    private final String text;
}
