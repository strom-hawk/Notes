package com.demoapps.notes.viewmodel;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.ViewModel;

public class HomeScreenViewModel extends ViewModel {

    public  void addNewNote(View view){
        Log.d("add", "add new note called");
    }
}
