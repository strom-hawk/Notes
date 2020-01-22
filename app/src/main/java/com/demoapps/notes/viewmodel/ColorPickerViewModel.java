package com.demoapps.notes.viewmodel;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import com.demoapps.notes.interfaces.CallBack;

public class ColorPickerViewModel extends ViewModel {

    private Context context;
    private CallBack callback;

    public ColorPickerViewModel(Context context, CallBack callback) {
        this.context = context;
        this.callback = callback;
    }

}
