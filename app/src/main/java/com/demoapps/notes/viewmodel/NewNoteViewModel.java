package com.demoapps.notes.viewmodel;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.demoapps.notes.interfaces.CallBack;

public class NewNoteViewModel extends ViewModel {

    private Context context;
    private CallBack callBack;

    public NewNoteViewModel(Context context, CallBack callBack) {
        this.context = context;
        this.callBack = callBack;
    }
}
