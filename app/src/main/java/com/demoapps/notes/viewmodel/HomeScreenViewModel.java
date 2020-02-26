package com.demoapps.notes.viewmodel;

import android.content.Context;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.demoapps.notes.R;
import com.demoapps.notes.interfaces.CallBack;
import com.demoapps.notes.model.HomeScreenModel;
import com.demoapps.notes.utils.ApplicationConstants;

import java.util.ArrayList;

public class HomeScreenViewModel extends ViewModel {
    MutableLiveData<ArrayList<HomeScreenModel>> notesLiveData;
    ArrayList<HomeScreenModel> notesArrayList;

    private Context context;
    private CallBack callBack;


    public HomeScreenViewModel() {
        notesLiveData = new MutableLiveData<>();
        init();
    }

    public HomeScreenViewModel(Context context, CallBack callBack) {
        this.context = context;
        this.callBack = callBack;
    }

    public MutableLiveData<ArrayList<HomeScreenModel>> getNotesLiveData() {
        return notesLiveData;
    }

    public void init(){
        populateList();
        notesLiveData.setValue(notesArrayList);
    }

    public void populateList(){
        HomeScreenModel homeScreenModel = new HomeScreenModel();
        homeScreenModel.setNoteTitle("note1");
        homeScreenModel.setNoteColor("red");
        homeScreenModel.setNoteDate("date");
        homeScreenModel.setNoteText("loren ipsum loren ipsum loren ipsum loren ipsum loren ipsum loren ipsum");
        notesArrayList = new ArrayList<>();
        notesArrayList.add(homeScreenModel);
        notesArrayList.add(homeScreenModel);
        notesArrayList.add(homeScreenModel);
        notesArrayList.add(homeScreenModel);

    }

    public  void addNewNote(View view){
        callBack.onSuccess(context.getString(R.string.add_new), ApplicationConstants.EMPTY_STRING, ApplicationConstants.EMPTY_STRING);
    }
}
