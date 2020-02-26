package com.demoapps.notes.viewmodel;

import android.content.Context;
import android.view.View;

import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.demoapps.notes.R;
import com.demoapps.notes.interfaces.CallBack;
import com.demoapps.notes.interfaces.NoteDAO;
import com.demoapps.notes.model.NewNoteModel;
import com.demoapps.notes.utils.AppDatabase;
import com.demoapps.notes.utils.ApplicationConstants;

import java.util.List;

public class NewNoteViewModel extends ViewModel {

    private Context context;
    private CallBack callBack;
    private NewNoteModel newNoteModel;

    public NewNoteViewModel(Context context, CallBack callBack) {
        this.context = context;
        this.callBack = callBack;
    }

    public void toggleColorPicker(View view){
        callBack.onSuccess(ApplicationConstants.TOGGLE_COLOR_VIEW, ApplicationConstants.EMPTY_STRING,ApplicationConstants.EMPTY_STRING);
    }

    public void changeColor(View view){
        callBack.onSuccess(ApplicationConstants.COLOR_PICKED, ApplicationConstants.EMPTY_STRING, String.valueOf(view.getId()));
    }

    public void addNewNote(View view){
        AppDatabase appDatabase = Room.databaseBuilder(context, AppDatabase.class, "db-notes").allowMainThreadQueries().build();
        NoteDAO noteDAO = appDatabase.getNotes();
        newNoteModel = new NewNoteModel();
        System.out.println("Notes title ----> " + newNoteModel.getNoteTitle());
        //newNoteModel.setNoteTitle("demo note title");
        //newNoteModel.setNoteText("checking note text");

        //noteDAO.insert(newNoteModel);

        List<NewNoteModel> notes = noteDAO.getNotes();
        System.out.println("retrieved notes ----> \n" + notes.size());
    }
}
