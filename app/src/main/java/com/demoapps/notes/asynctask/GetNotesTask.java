package com.demoapps.notes.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.demoapps.notes.utils.AppDatabase;
import com.demoapps.notes.utils.NoteEntity;

import java.util.List;

public class GetNotesTask extends AsyncTask<Void, Void, Boolean> {
    private Context context;
    private List<NoteEntity> retrivedNotes;

    public GetNotesTask(Context context) {
        this.context = context;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        retrivedNotes = AppDatabase.getInstance(context).getNotesDao().getNotes();
        return true;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if (aBoolean) { }
    }

    public List<NoteEntity> getRetrievedNotes(){
        return retrivedNotes;
    }
}