package com.demoapps.notes.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.demoapps.notes.utils.AppDatabase;
import com.demoapps.notes.utils.NoteEntity;

import java.util.List;

public class GetNotesTask extends AsyncTask<Void, Void, List<NoteEntity>> {
    private AppDatabase appDatabase;
    private List<NoteEntity> retrivedNotes;

    public GetNotesTask(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    @Override
    protected List<NoteEntity> doInBackground(Void... voids) {
        return appDatabase.getNotesDao().getNotes();
    }

    public List<NoteEntity> getRetrievedNotes(){
        return retrivedNotes;
    }
}