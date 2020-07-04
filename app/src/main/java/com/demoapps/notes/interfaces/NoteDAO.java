package com.demoapps.notes.interfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.demoapps.notes.model.NewNoteModel;
import com.demoapps.notes.utils.ApplicationConstants;

import java.util.List;

@Dao
public interface NoteDAO {

    @Insert
    public void insert(NewNoteModel... newNoteModes);

    @Query("SELECT * FROM " + ApplicationConstants.TABLE_NAME)
    public List<NewNoteModel> getNotes();

    @Query("DELETE FROM "+ ApplicationConstants.TABLE_NAME +" WHERE noteTitle = :noteTitle")
    public void deleteNote(String noteTitle);
}
