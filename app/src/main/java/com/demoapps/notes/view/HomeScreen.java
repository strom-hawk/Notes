package com.demoapps.notes.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.demoapps.notes.model.HomeScreenModel;
import com.demoapps.notes.R;
import com.demoapps.notes.viewmodel.HomeScreenViewModel;
import com.demoapps.notes.databinding.ActivityHomescreenBinding;

public class HomeScreen extends AppCompatActivity implements View.OnClickListener {
    private HomeScreenModel homeScreenModel;
    private HomeScreenViewModel homeScreenViewModel;
    Toolbar toolbar;
    ImageButton addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_homescreen);
        initDataBinding();
        initToolBar();
        initViews();
    }

    private void initDataBinding(){
        homeScreenModel = new HomeScreenModel();
        homeScreenViewModel = new HomeScreenViewModel();
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
        addButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}
