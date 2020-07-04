package com.demoapps.notes.viewmodel;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.demoapps.notes.R;
import com.demoapps.notes.interfaces.CallBack;
import com.demoapps.notes.interfaces.NoteDAO;
import com.demoapps.notes.model.NewNoteModel;
import com.demoapps.notes.utils.AppDatabase;
import com.demoapps.notes.utils.ApplicationConstants;
import com.demoapps.notes.utils.CommonUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class NewNoteViewModel extends ViewModel {

    private Context context;
    private CallBack callBack;
    private NewNoteModel newNoteModel;
    private AppDatabase appDatabase;
    private NoteDAO noteDAO;

    public NewNoteViewModel(Context context, CallBack callBack) {
        this.context = context;
        this.callBack = callBack;
        newNoteModel = new NewNoteModel();
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "db-notes").allowMainThreadQueries().build();
        noteDAO = appDatabase.getNotes();
    }

    public void toggleColorPicker(View view){
        callBack.onSuccess(ApplicationConstants.TOGGLE_COLOR_VIEW, ApplicationConstants.EMPTY_STRING,ApplicationConstants.EMPTY_STRING);
    }

    public void changeColor(View view){
        callBack.onSuccess(ApplicationConstants.COLOR_PICKED, ApplicationConstants.EMPTY_STRING, String.valueOf(view.getId()));
    }

    public void addNewNote(View view){
        if(null != newNoteModel.getNoteTitle() && newNoteModel.getNoteTitle().length() > ApplicationConstants.NUMBER_ZERO){
            if(!checkExistingNotes()){
                noteDAO.insert(newNoteModel);
                callBack.onSuccess(ApplicationConstants.NEW_NOTE_SAVED, ApplicationConstants.EMPTY_STRING, ApplicationConstants.EMPTY_STRING);
            }else{
                CommonUtils.showAlertDialog(context, context.getResources().getString(R.string.error_message),
                        context.getResources().getString(R.string.same_note_error_msg), context.getResources().getString(R.string.ok_button_msg));
            }
        } else{
            CommonUtils.showAlertDialog(context, context.getResources().getString(R.string.error_message),
                    context.getResources().getString(R.string.title_hint), context.getResources().getString(R.string.ok_button_msg));
        }

    }

    public void deleteNote(View view){
        System.out.println("delete called");
        System.out.println("note title ---> " + newNoteModel.getNoteTitle());
        System.out.println("note text ---> " + newNoteModel.getNoteText());
        noteDAO.deleteNote(newNoteModel.getNoteTitle());
        callBack.onSuccess(ApplicationConstants.NOTE_DELETED, ApplicationConstants.EMPTY_STRING, ApplicationConstants.EMPTY_STRING);
    }

    private boolean checkExistingNotes(){
        List<NewNoteModel> notes = noteDAO.getNotes();
        for(int i=0; i<notes.size(); i++){
            if(notes.get(i).getNoteTitle().equalsIgnoreCase(newNoteModel.getNoteTitle())){
                return true;
            }
        }
        return false;
    }

    public TextWatcher notesTitleWatcher(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                newNoteModel.setNoteTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };
    }

    public TextWatcher notesTextWatcher(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                newNoteModel.setNoteText(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };
    }

    public String getCurrentDate(){
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ApplicationConstants.DATE_FORMAT, Locale.ENGLISH);
        newNoteModel.setLastUpdatedDate(simpleDateFormat.format(date));
        return simpleDateFormat.format(date);
    }

    public void setUpdateData(String noteTitle, String noteText){
        newNoteModel.setNoteTitle(noteTitle);
        newNoteModel.setNoteText(noteText);
    }

}
