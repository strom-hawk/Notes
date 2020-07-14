package com.demoapps.notes.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

import com.demoapps.notes.R;
import com.demoapps.notes.databinding.ActivityAddNewBinding;
import com.demoapps.notes.interfaces.CallBack;
import com.demoapps.notes.model.AddNewModel;
import com.demoapps.notes.viewmodel.AddNewViewModel;

public class AddNewActivity extends AppCompatActivity implements CallBack {
    private AddNewModel addNewModel;
    private AddNewViewModel addNewViewModel;
    private ConstraintLayout addNewNotesLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        initViews();
    }

    private void initDataBinding() {
        addNewModel = new AddNewModel();
        addNewViewModel = new AddNewViewModel(this, this);
        ActivityAddNewBinding activityAddNewBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_new);
        activityAddNewBinding.setAddNewModel(addNewModel);
        activityAddNewBinding.setAddNewViewModel(addNewViewModel);
    }

    private void initViews() {
        addNewNotesLayout = findViewById(R.id.notes_layout);
    }

    @Override
    public void onSuccess(String txnType, String txnStatus, String txnMessage) {
        Intent intent = null;
        if (txnType.equalsIgnoreCase(getString(R.string.add_new_note))) {
            intent = new Intent(this, NewNoteActivity.class);
        } else if (txnType.equalsIgnoreCase(getString(R.string.add_new_checklist))) {
            Toast.makeText(this, getString(R.string.underdevelopment), Toast.LENGTH_SHORT).show();
        }
        if (null != intent) {
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onFailure(String txnStatus, String txnMessage) {

    }
}
