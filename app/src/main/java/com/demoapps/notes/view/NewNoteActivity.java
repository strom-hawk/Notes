package com.demoapps.notes.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.demoapps.notes.R;
import com.demoapps.notes.databinding.ActivityAddNewBinding;
import com.demoapps.notes.databinding.ActivityNewNoteBinding;
import com.demoapps.notes.interfaces.CallBack;
import com.demoapps.notes.model.NewNoteModel;
import com.demoapps.notes.utils.ApplicationConstants;
import com.demoapps.notes.viewmodel.NewNoteViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class NewNoteActivity extends AppCompatActivity implements CallBack {
    private NewNoteModel newNoteModel;
    private NewNoteViewModel newNoteViewModel;
    private Toolbar toolbar;
    private EditText titleEditText;
    private EditText noteEditText;
    private TextView dateTextView;
    private ImageButton doneImageButton;
    private Button colorButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        initToolBar();
        initViews();
        setDateOnNewNote();
    }

    private void initDataBinding(){
        newNoteModel = new NewNoteModel();
        newNoteViewModel = new NewNoteViewModel(this, this);
        ActivityNewNoteBinding activityNewNoteBinding = DataBindingUtil.setContentView(this, R.layout.activity_new_note);
        activityNewNoteBinding.setNewNoteModel(newNoteModel);
        activityNewNoteBinding.setNewNoteViewModel(newNoteViewModel);
    }

    private void initToolBar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getString(R.string.app_name));
    }

    private void initViews(){
        titleEditText = findViewById(R.id.newNoteEditText);
        noteEditText = findViewById(R.id.noteEditText);
        dateTextView = findViewById(R.id.dateTextView);
        doneImageButton = findViewById(R.id.doneImageButton);
        colorButton = findViewById(R.id.colorButton);
    }

    private void setDateOnNewNote(){
        Date date = Calendar.getInstance().getTime();
        //System.out.println("Un formatted date ---> " + date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ApplicationConstants.DATE_FORMAT, Locale.ENGLISH);
        String currentFormatedDate = simpleDateFormat.format(date);
        //System.out.println("Formatted date ---> " +currentFormatedDate);
        dateTextView.setText(currentFormatedDate);
    }

    @Override
    public void onSuccess(String txnType, String txnStatus, String txnMessage) {

    }

    @Override
    public void onFailure(String txnStatus, String txnMessage) {

    }
}
