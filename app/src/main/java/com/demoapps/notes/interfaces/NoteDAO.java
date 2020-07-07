package com.demoapps.notes.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.demoapps.notes.utils.ApplicationConstants;
import com.demoapps.notes.utils.NoteEntity;

import java.util.List;

@Dao
public interface NoteDAO {

    @Insert
    public void add(NoteEntity... noteEntities);

    @Query("SELECT * FROM " + ApplicationConstants.TABLE_NAME)
    public List<NoteEntity> getNotes();

    @Query("DELETE FROM "+ ApplicationConstants.TABLE_NAME +" WHERE note_title = :noteTitle")
    public void deleteNote(String noteTitle);

    @Query("UPDATE " + ApplicationConstants.TABLE_NAME + " SET " +
            "note_title=:noteTitle, " +
            "note_text=:noteText, " +
            "note_lastUpdated=:lastUpdatedDate WHERE note_title=:oldNoteTitle")
    public void updateNote(String noteTitle, String noteText, String lastUpdatedDate, String oldNoteTitle);
}
