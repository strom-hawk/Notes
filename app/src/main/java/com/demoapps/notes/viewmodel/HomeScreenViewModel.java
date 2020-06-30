package com.demoapps.notes.viewmodel;

import android.content.Context;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.demoapps.notes.R;
import com.demoapps.notes.interfaces.CallBack;
import com.demoapps.notes.interfaces.NoteDAO;
import com.demoapps.notes.model.HomeScreenModel;
import com.demoapps.notes.model.NewNoteModel;
import com.demoapps.notes.utils.AppDatabase;
import com.demoapps.notes.utils.ApplicationConstants;

import java.util.ArrayList;
import java.util.List;

public class HomeScreenViewModel extends ViewModel {
    MutableLiveData<ArrayList<HomeScreenModel>> notesLiveData;
    ArrayList<HomeScreenModel> notesArrayList;
    private Context context;
    private CallBack callBack;
    private AppDatabase appDatabase;
    private NoteDAO noteDAO;

    public HomeScreenViewModel(Context context) {
        this.context = context;
        notesLiveData = new MutableLiveData<>();
        init();
    }

    public HomeScreenViewModel(Context context, CallBack callBack) {
        this.callBack = callBack;
        this.context = context;
        notesLiveData = new MutableLiveData<>();
        
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "db-notes").allowMainThreadQueries().build();
        noteDAO = appDatabase.getNotes();
        init();
    }

    public MutableLiveData<ArrayList<HomeScreenModel>> getNotesLiveData() {
        return notesLiveData;
    }

    private void init(){
        populateList();
    }

    private void populateList(){

        //HomeScreenModel homeScreenModel = new HomeScreenModel();
        /*homeScreenModel.setNoteTitle("note1");
        homeScreenModel.setNoteColor("red");
        homeScreenModel.setNoteText("loren ipsum loren ipsum loren ipsum loren ipsum loren ipsum loren ipsum");
        notesArrayList = new ArrayList<>();
        notesArrayList.add(homeScreenModel);
        notesArrayList.add(homeScreenModel);
        notesArrayList.add(homeScreenModel);
        notesArrayList.add(homeScreenModel);*/

        List<NewNoteModel> notes = null;

        try{
            appDatabase = Room.databaseBuilder(context, AppDatabase.class, "db-notes").allowMainThreadQueries().build();
            noteDAO = appDatabase.getNotes();
            notes = noteDAO.getNotes();
        }catch(Exception e){
            e.printStackTrace();
        }



        notesArrayList = new ArrayList<>();

        if(null != notes && notes.size() > 0){
            for(int i=0; i<notes.size(); i++){
                HomeScreenModel homeScreenModel = new HomeScreenModel();
                homeScreenModel.setNoteTitle(notes.get(i).getNoteTitle());
                homeScreenModel.setNoteColor(notes.get(i).getNoteColor());
                homeScreenModel.setNoteText(notes.get(i).getNoteText());
                notesArrayList.add(homeScreenModel);
            }
        }

        System.out.println("-----notes array list size ---> " + notesArrayList.size());
        notesLiveData.setValue(notesArrayList);
    }

    public  void addNewNote(View view){
        callBack.onSuccess(context.getString(R.string.add_new), ApplicationConstants.EMPTY_STRING, ApplicationConstants.EMPTY_STRING);
    }
}
