package com.demoapps.notes.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.demoapps.notes.interfaces.CallBack;
import com.demoapps.notes.model.HomeScreenModel;
import com.demoapps.notes.R;
import com.demoapps.notes.viewmodel.HomeScreenViewModel;
import com.demoapps.notes.databinding.ActivityHomescreenBinding;

public class HomeScreenActivity extends AppCompatActivity implements CallBack {
    private HomeScreenModel homeScreenModel;
    private HomeScreenViewModel homeScreenViewModel;
    private Toolbar toolbar;
    private ImageButton addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        initToolBar();
        initViews();
    }

    private void initDataBinding(){
        homeScreenModel = new HomeScreenModel();
        homeScreenViewModel = new HomeScreenViewModel(this, this);
        ActivityHomescreenBinding activityHomescreenBinding = DataBindingUtil.setContentView(this, R.layout.activity_homescreen);
        activityHomescreenBinding.setHomeScreenHandler(homeScreenViewModel);
        activityHomescreenBinding.setHomeScreenModel(homeScreenModel);
    }

    private void initToolBar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getString(R.string.app_name));
    }

    private void initViews(){
        addButton = (ImageButton) findViewById(R.id.newNote);
    }

    @Override
    public void onSuccess(String txnType, String txnStatus, String txnMessage) {
        if(txnType.equalsIgnoreCase(getString(R.string.add_new))){
            Intent intent = new Intent(this, AddNewActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onFailure(String txnStatus, String txnMessage) {

    }
}
