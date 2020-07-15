package com.demoapps.notes.utils.interfaces;

import android.content.Context;
import android.os.AsyncTask;

import androidx.test.runner.AndroidJUnit4;

import com.demoapps.notes.asynctask.GetNotesTask;
import com.demoapps.notes.interfaces.NoteDAO;
import com.demoapps.notes.utils.AppDatabase;
import com.demoapps.notes.asynctask.InsertNoteTask;
import com.demoapps.notes.utils.NoteEntity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.annotation.Config;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RunWith(AndroidJUnit4.class)
@Config(manifest= Config.NONE)
public class NoteDAOTest {
    private Context context = Mockito.mock(Context.class);
    private AppDatabase appDatabase;
    private NoteEntity noteEntity;
    private List<NoteEntity> notes = null;

    public void initDatabase() {
        appDatabase = AppDatabase.getInstanceForTestCase(context);
    }

    public void closeDatabase(){
        appDatabase.close();
    }

    @Test
    public void testInsertionDAO() {
        initDatabase();
        initNewNote();

        new InsertNoteTask(appDatabase, noteEntity).execute();

        Assert.assertEquals("Note Title", noteEntity.getNoteTitle());
        Assert.assertEquals("Note Text", noteEntity.getNoteText());
        Assert.assertEquals("Default Date", noteEntity.getLastUpdatedDate());
        Assert.assertEquals("Blue", noteEntity.getNoteColor());

        closeDatabase();
    }

    @Test
    public void testGetDataDAO(){
        initDatabase();

        try {
            notes = new GetNotesTask(appDatabase).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("note title ---> " + notes.size());

        closeDatabase();
    }

    private void initNewNote() {
        noteEntity = new NoteEntity();
        noteEntity.setNoteTitle("Note Title");
        noteEntity.setNoteText("Note Text");
        noteEntity.setLastUpdatedDate("Default Date");
        noteEntity.setNoteColor("Blue");
    }


}
