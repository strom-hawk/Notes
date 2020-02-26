package com.demoapps.notes.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.demoapps.notes.model.NewNoteModel;

import java.util.List;

@Dao
public interface NoteDAO {

    @Insert
    public void insert(NewNoteModel... newNoteModes);

    @Query("SELECT * FROM notes")
    public List<NewNoteModel> getNotes();
}
