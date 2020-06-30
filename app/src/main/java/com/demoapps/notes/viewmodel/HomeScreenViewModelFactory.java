package com.demoapps.notes.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class HomeScreenViewModelFactory implements ViewModelProvider.Factory {

    private Context context;

    public HomeScreenViewModelFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new HomeScreenViewModel(context);
    }
}
