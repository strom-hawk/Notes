package com.demoapps.notes.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.demoapps.notes.utils.AppDatabase;

public class DeleteNoteTask extends AsyncTask<Void, Void, Boolean> {
    private Context context;
    private String noteTitle;

    public DeleteNoteTask(Context context, String noteTitle) {
        this.context = context;
        this.noteTitle = noteTitle;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        AppDatabase.getInstance(context).getNotesDao().deleteNote(noteTitle);
        return true;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if (aBoolean) {
        }
    }
}