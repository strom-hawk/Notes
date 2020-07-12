package com.demoapps.notes.viewmodel;

import android.content.Context;
import android.os.AsyncTask;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.lifecycle.ViewModel;

import com.demoapps.notes.R;
import com.demoapps.notes.interfaces.CallBack;
import com.demoapps.notes.interfaces.NoteDAO;
import com.demoapps.notes.model.NewNoteModel;
import com.demoapps.notes.utils.AppDatabase;
import com.demoapps.notes.utils.ApplicationConstants;
import com.demoapps.notes.utils.CommonUtils;
import com.demoapps.notes.utils.NoteEntity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NewNoteViewModel extends ViewModel {

    private Context context;
    private CallBack callBack;
    private NoteEntity noteEntity;
    private NewNoteModel newNoteModel;
    private AppDatabase appDatabase;
    private NoteDAO noteDAO;
    private boolean isUpdate = false;
    private String oldNoteTitle = ApplicationConstants.EMPTY_STRING;

    public NewNoteViewModel(Context context, CallBack callBack) {
        this.context = context;
        this.callBack = callBack;
        newNoteModel = new NewNoteModel();
        noteEntity = new NoteEntity();
        appDatabase = AppDatabase.getInstance(context);
        noteDAO = appDatabase.getNotesDao();
    }

    public void toggleColorPicker(View view){
        callBack.onSuccess(ApplicationConstants.TOGGLE_COLOR_VIEW, ApplicationConstants.EMPTY_STRING,ApplicationConstants.EMPTY_STRING);
    }

    public void changeColor(View view){
        callBack.onSuccess(ApplicationConstants.COLOR_PICKED, ApplicationConstants.EMPTY_STRING, String.valueOf(view.getId()));
    }

    public void addNewNote(View view){
        if(isUpdate){
            if(null != newNoteModel.getNoteTitle() && newNoteModel.getNoteTitle().length() > ApplicationConstants.NUMBER_ZERO){
                new UpdateNoteTask(
                        context,
                        newNoteModel.getNoteTitle(),
                        newNoteModel.getNoteText(),
                        newNoteModel.getLastUpdatedDate(),
                        newNoteModel.getNoteColor(),
                        oldNoteTitle).execute();
                callBack.onSuccess(ApplicationConstants.NOTE_UPDATED, ApplicationConstants.EMPTY_STRING, ApplicationConstants.EMPTY_STRING);
            } else{
                CommonUtils.showAlertDialog(context, context.getResources().getString(R.string.error_message),
                        context.getResources().getString(R.string.title_hint), context.getResources().getString(R.string.ok_button_msg));
            }
        }else{
            if(null != newNoteModel.getNoteTitle() && newNoteModel.getNoteTitle().length() > ApplicationConstants.NUMBER_ZERO){
                if(!checkExistingNotes()){
                    insertDataToEntity(newNoteModel, noteEntity);
                    new InsertNoteTask(
                            context,
                            noteEntity).execute();
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
    }

    public void deleteNote(View view){
        new DeleteNoteTask(context, newNoteModel.getNoteTitle()).execute();
        callBack.onSuccess(ApplicationConstants.NOTE_DELETED, ApplicationConstants.EMPTY_STRING, ApplicationConstants.EMPTY_STRING);
    }

    private boolean checkExistingNotes(){
        List<NoteEntity> notes = noteDAO.getNotes();
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

    public void setUpdateData(String noteTitle, String noteText, boolean updateNote){
        newNoteModel.setNoteTitle(noteTitle);
        newNoteModel.setNoteText(noteText);
        isUpdate = updateNote;
        oldNoteTitle = noteTitle;
    }

    public void changeNoteColor(String noteBackGroundColor){
        newNoteModel.setNoteColor(noteBackGroundColor);
    }

    public void insertDataToEntity(NewNoteModel newNoteModel, NoteEntity noteEntity){
        noteEntity.setNoteTitle(newNoteModel.getNoteTitle());
        noteEntity.setNoteText(newNoteModel.getNoteText());
        noteEntity.setLastUpdatedDate(newNoteModel.getLastUpdatedDate());
        if(null == newNoteModel.getNoteColor()){
            noteEntity.setNoteColor(ApplicationConstants.NOTE_BG_BLUE);
        }else{
            noteEntity.setNoteColor(newNoteModel.getNoteColor());
        }
    }

    private static class InsertNoteTask extends AsyncTask<Void, Void, Boolean>{
        private Context context;
        private NoteEntity noteEntity;

        InsertNoteTask(Context context, NoteEntity noteEntity){
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
            if(aBoolean){ }
        }
    }

    private static class UpdateNoteTask extends AsyncTask<Void, Void, Boolean>{
        private Context context;
        private String noteTitle;
        private String noteText;
        private String lastUpdatedDate;
        private String noteColor;
        private String oldNoteTitle;

        UpdateNoteTask(Context context, String noteTitle, String noteText, String lastUpdatedDate, String noteColor, String oldNoteTitle){
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
            if(aBoolean){ }
        }
    }

    private static class DeleteNoteTask extends AsyncTask<Void, Void, Boolean>{
        private Context context;
        private String noteTitle;

        DeleteNoteTask(Context context, String noteTitle){
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
            if(aBoolean){ }
        }
    }

}
