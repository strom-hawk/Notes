package com.demoapps.notes.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.demoapps.notes.utils.AppDatabase;
import com.demoapps.notes.utils.NoteEntity;

public class InsertNoteTask extends AsyncTask<Void, Void, Boolean> {
    private Context context;
    private NoteEntity noteEntity;

    public InsertNoteTask(Context context, NoteEntity noteEntity) {
        this.context = context;
        this.noteEntity = noteEntity;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        AppDatabase.getInstance(context).getNotesDao().add(noteEntity);
        return true;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if (aBoolean) { }
    }
}