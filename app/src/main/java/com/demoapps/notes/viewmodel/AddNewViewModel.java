package com.demoapps.notes.viewmodel;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.demoapps.notes.R;
import com.demoapps.notes.interfaces.CallBack;
import com.demoapps.notes.utils.ApplicationConstants;

public class AddNewViewModel extends ViewModel {

    private Context context;
    private CallBack callBack;

    public AddNewViewModel(Context context, CallBack callBack) {
        this.context = context;
        this.callBack = callBack;
    }

    public void addNewNote(View view) {
        callBack.onSuccess(context.getString(R.string.add_new_note), ApplicationConstants.EMPTY_STRING, ApplicationConstants.EMPTY_STRING);
    }

    public void addNewCheckList(View view) {
        callBack.onSuccess(context.getString(R.string.add_new_checklist), ApplicationConstants.EMPTY_STRING, ApplicationConstants.EMPTY_STRING);
    }
}
