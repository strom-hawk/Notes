package com.demoapps.notes.viewmodel;

import android.content.Context;
import android.view.View;

import androidx.lifecycle.ViewModel;

import com.demoapps.notes.R;
import com.demoapps.notes.interfaces.CallBack;
import com.demoapps.notes.utils.ApplicationConstants;

public class NewNoteViewModel extends ViewModel {

    private Context context;
    private CallBack callBack;

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
}
