package com.demoapps.notes.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.demoapps.notes.utils.AppDatabase;

public class UpdateNoteTask extends AsyncTask<Void, Void, Boolean> {
    private Context context;
    private String noteTitle;
    private String noteText;
    private String lastUpdatedDate;
    private String noteColor;
    private String oldNoteTitle;

    public UpdateNoteTask(Context context, String noteTitle, String noteText, String lastUpdatedDate, String noteColor, String oldNoteTitle) {
        this.context = context;
        this.noteTitle = noteTitle;
        this.noteText = noteText;
        this.lastUpdatedDate = lastUpdatedDate;
        this.noteColor = noteColor;
        this.oldNoteTitle = oldNoteTitle;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        AppDatabase.getInstance(context).getNotesDao().updateNote(
                noteTitle,
                noteText,
                lastUpdatedDate,
                noteColor,
                oldNoteTitle);
        return true;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if (aBoolean) {
        }
    }
}