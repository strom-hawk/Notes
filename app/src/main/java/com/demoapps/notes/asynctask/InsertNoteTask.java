package com.demoapps.notes.asynctask;

import android.os.AsyncTask;

import com.demoapps.notes.utils.AppDatabase;
import com.demoapps.notes.utils.NoteEntity;

public class InsertNoteTask extends AsyncTask<Void, Void, Boolean> {
    private NoteEntity noteEntity;
    private AppDatabase appDatabase;

    public InsertNoteTask( AppDatabase appDatabase, NoteEntity noteEntity) {
        this.appDatabase = appDatabase;
        this.noteEntity = noteEntity;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        appDatabase.getNotesDao().add(noteEntity);
        return true;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if (aBoolean) { }
    }
}