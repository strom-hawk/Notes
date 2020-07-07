package com.demoapps.notes.utils;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.demoapps.notes.interfaces.NoteDAO;
import com.demoapps.notes.model.NewNoteModel;

@Database(entities = {NoteEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public static AppDatabase appDatabase;

    public abstract NoteDAO getNotesDao();

    public static AppDatabase getInstance(Context context){
        if(null == appDatabase){
            appDatabase = buildDatabaseInstance(context);
        }
        return appDatabase;
    }

    private static AppDatabase buildDatabaseInstance(Context context){
        return Room.databaseBuilder(context, AppDatabase.class, ApplicationConstants.DB_NAME).allowMainThreadQueries().build();
    }
}
