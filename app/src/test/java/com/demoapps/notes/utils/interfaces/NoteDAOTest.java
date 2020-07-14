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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.annotation.Config;

import java.util.List;

@RunWith(AndroidJUnit4.class)
@Config(manifest= Config.NONE)
public class NoteDAOTest {
    private Context context = Mockito.mock(Context.class);
    private AppDatabase appDatabase;
    private NoteDAO noteDAO;
    private NoteEntity noteEntity;
    private List<NoteEntity> notes = null;

    @Before
    public void init() {
        appDatabase = AppDatabase.getInstanceForTestCase(context);
        noteDAO = appDatabase.getNotesDao();

    }

    @Test
    public void testInsertionDAO() {
        initNewNote();
        new InsertNoteTask(context, noteEntity);

        Assert.assertEquals("Note Title", noteEntity.getNoteTitle());
        Assert.assertEquals("Note Text", noteEntity.getNoteText());
        Assert.assertEquals("Default Date", noteEntity.getLastUpdatedDate());
        Assert.assertEquals("Blue", noteEntity.getNoteColor());
    }

    private void initNewNote() {
        noteEntity = new NoteEntity();
        noteEntity.setNoteTitle("Note Title");
        noteEntity.setNoteText("Note Text");
        noteEntity.setLastUpdatedDate("Default Date");
        noteEntity.setNoteColor("Blue");
    }


}
