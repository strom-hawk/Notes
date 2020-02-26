package com.demoapps.notes.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.demoapps.notes.adapter.NotesAdapter;
import com.demoapps.notes.interfaces.CallBack;
import com.demoapps.notes.model.HomeScreenModel;
import com.demoapps.notes.R;
import com.demoapps.notes.viewmodel.HomeScreenViewModel;
import com.demoapps.notes.databinding.ActivityHomescreenBinding;

import java.util.ArrayList;
import java.util.Observable;

import javax.inject.Inject;

public class HomeScreenActivity extends AppCompatActivity implements CallBack, LifecycleOwner {
    private HomeScreenModel homeScreenModel;
    private HomeScreenViewModel homeScreenViewModel;
    private Toolbar toolbar;
    private ImageButton addButton;

    private RecyclerView recyclerView;
    private NotesAdapter notesAdapter;
    private ActivityHomescreenBinding activityHomescreenBinding;
    private HomeScreenActivity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        initToolBar();
        initViews();
        initAdapter();
    }

    private void initDataBinding(){
        homeScreenModel = new HomeScreenModel();
        homeScreenViewModel = new HomeScreenViewModel();
        homeScreenViewModel = new HomeScreenViewModel(this, this);

        activityHomescreenBinding = DataBindingUtil.setContentView(this, R.layout.activity_homescreen);
        activityHomescreenBinding.setHomeScreenHandler(homeScreenViewModel);
        activityHomescreenBinding.setHomeScreenModel(homeScreenModel);
    }

    private void initToolBar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getString(R.string.app_name));
    }

    private void initViews(){
        context = this;
        addButton = (ImageButton) findViewById(R.id.newNote);
        recyclerView = (RecyclerView) findViewById(R.id.notesRecyclerView);
    }

    private void initAdapter(){
        homeScreenViewModel = ViewModelProviders.of(context).get(HomeScreenViewModel.class);
        homeScreenViewModel.getNotesLiveData().observe(context, notesListUpdateObserver);
    }

    Observer<ArrayList<HomeScreenModel>> notesListUpdateObserver = new Observer<ArrayList<HomeScreenModel>>() {
        @Override
        public void onChanged(ArrayList<HomeScreenModel> homeScreenModels) {
            notesAdapter = new NotesAdapter(context, homeScreenModels);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(notesAdapter);
        }
    };

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
