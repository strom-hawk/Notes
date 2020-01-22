package com.demoapps.notes.view;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.demoapps.notes.R;
import com.demoapps.notes.databinding.ActivityColorPickerBinding;
import com.demoapps.notes.interfaces.CallBack;
import com.demoapps.notes.model.ColorPickerModel;
import com.demoapps.notes.viewmodel.ColorPickerViewModel;

public class ColorPickerActivity extends AppCompatActivity implements CallBack {
    private ColorPickerModel colorPickerModel;
    private ColorPickerViewModel colorPickerViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
    }

    private void initDataBinding(){
        colorPickerModel = new ColorPickerModel();
        colorPickerViewModel = new ColorPickerViewModel(this, this);
        ActivityColorPickerBinding activityColorPickerBinding = DataBindingUtil.setContentView(this, R.layout.activity_color_picker);
        activityColorPickerBinding.setModel(colorPickerModel);
        activityColorPickerBinding.setViewModel(colorPickerViewModel);
    }

    @Override
    public void onSuccess(String txnType, String txnStatus, String txnMessage) {

    }

    @Override
    public void onFailure(String txnStatus, String txnMessage) {

    }
}
