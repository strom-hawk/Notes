package com.demoapps.notes.utils;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.demoapps.notes.interfaces.NoteDAO;
import com.demoapps.notes.model.NewNoteModel;

@Database(entities = {NewNoteModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NoteDAO getNotes();
}
