package com.demoapps.notes.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

import com.demoapps.notes.R;
import com.demoapps.notes.databinding.ActivityAddNewBinding;
import com.demoapps.notes.databinding.ActivityNewNoteBinding;
import com.demoapps.notes.interfaces.CallBack;
import com.demoapps.notes.model.NewNoteModel;
import com.demoapps.notes.utils.ApplicationConstants;
import com.demoapps.notes.utils.CommonUtils;
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
    private ImageButton deleteImageButton;
    private Button colorButton;
    private ConstraintLayout colorView;
    private ConstraintLayout dateLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        initToolBar();
        initViews();
        getIntentExtras();
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
        colorView = findViewById(R.id.colorPickerView);
        dateLayout = findViewById(R.id.dateLayout);
        deleteImageButton = findViewById(R.id.deleteImageButton);
    }

    public void getIntentExtras(){
        if(getIntent().hasExtra(ApplicationConstants.EDIT_NOTE_FLAG)){
            boolean editNote = getIntent().hasExtra(ApplicationConstants.EDIT_NOTE_FLAG);
            if(editNote){
                if(getIntent().hasExtra(ApplicationConstants.NOTE_TITLE)){
                    titleEditText.setText(getIntent().getExtras().getString(ApplicationConstants.NOTE_TITLE));
                }
                if(getIntent().hasExtra(ApplicationConstants.NOTE_TEXT)){
                    noteEditText.setText(getIntent().getExtras().getString(ApplicationConstants.NOTE_TEXT));
                }
                if(getIntent().hasExtra(ApplicationConstants.NOTE_DATE)){
                    dateTextView.setText(getIntent().getExtras().getString(ApplicationConstants.NOTE_DATE));
                }
                if(getIntent().hasExtra(ApplicationConstants.NOTE_COLOR)){
                    setBackgroundOnUpdate(getIntent().getExtras().getString(ApplicationConstants.NOTE_COLOR));
                }
                deleteImageButton.setVisibility(View.VISIBLE);
                newNoteViewModel.setUpdateData(titleEditText.getText().toString(),
                        noteEditText.getText().toString(), true);
            }else{
                deleteImageButton.setVisibility(View.GONE);
            }
        }else{
            deleteImageButton.setVisibility(View.GONE);
        }
    }

    @Override
    public void onSuccess(String txnType, String txnStatus, String txnMessage) {
        if(txnType.equalsIgnoreCase(ApplicationConstants.TOGGLE_COLOR_VIEW)){
            toggleColorPickerView();
        } else if(txnType.equalsIgnoreCase(ApplicationConstants.COLOR_PICKED)){
            changeNoteColor(txnMessage);
            colorView.setVisibility(View.GONE);
        } else if(txnType.equalsIgnoreCase(ApplicationConstants.NEW_NOTE_SAVED)){
            CommonUtils.showAlertDialogWithFinishActivity(this, ApplicationConstants.HIDE_DIALOG_TITLE,
                    getResources().getString(R.string.new_note_noted), getResources().getString(R.string.ok_button_msg), true);
        } else if(txnType.equalsIgnoreCase(ApplicationConstants.NOTE_DELETED)){
            CommonUtils.showAlertDialogWithFinishActivity(this, ApplicationConstants.HIDE_DIALOG_TITLE,
                    getResources().getString(R.string.note_deleted), getResources().getString(R.string.ok_button_msg), true);
        } else if(txnType.equalsIgnoreCase(ApplicationConstants.NOTE_UPDATED)){
            CommonUtils.showAlertDialogWithFinishActivity(this, ApplicationConstants.HIDE_DIALOG_TITLE,
                    getResources().getString(R.string.note_updated), getResources().getString(R.string.ok_button_msg), true);
        }
    }

    private void toggleColorPickerView(){
        if(colorView.getVisibility() == View.VISIBLE){
            colorView.setVisibility(View.GONE);
            noteEditText.setFocusableInTouchMode(true);
            noteEditText.setFocusable(true);

        }else{
            colorView.setVisibility(View.VISIBLE);
            noteEditText.setFocusable(false);
        }
    }

    private void changeNoteColor(String txnMessage){
        int viewId = Integer.parseInt(txnMessage);
        switch(viewId){
            case R.id.colorButton1:
                colorButton.setBackgroundColor(this.getResources().getColor(R.color.noteColor1));
                toolbar.setBackgroundColor(getResources().getColor(R.color.toolbarNoteColor1));
                dateLayout.setBackgroundColor(getResources().getColor(R.color.noteBgColor1));
                noteEditText.setBackgroundColor(getResources().getColor(R.color.noteBgColor1));
                newNoteViewModel.changeNoteColor(ApplicationConstants.NOTE_BG_BLUE);
                break;
            case R.id.colorButton2:
                colorButton.setBackgroundColor(getResources().getColor(R.color.noteColor2));
                toolbar.setBackgroundColor(getResources().getColor(R.color.toolbarNoteColor2));
                dateLayout.setBackgroundColor(getResources().getColor(R.color.noteBgColor2));
                noteEditText.setBackgroundColor(getResources().getColor(R.color.noteBgColor2));
                newNoteViewModel.changeNoteColor(ApplicationConstants.NOTE_BG_RED);
                break;
            case R.id.colorButton3:
                colorButton.setBackgroundColor(getResources().getColor(R.color.noteColor3));
                toolbar.setBackgroundColor(getResources().getColor(R.color.toolbarNoteColor3));
                dateLayout.setBackgroundColor(getResources().getColor(R.color.noteBgColor3));
                noteEditText.setBackgroundColor(getResources().getColor(R.color.noteBgColor3));
                newNoteViewModel.changeNoteColor(ApplicationConstants.NOTE_BG_PURPLE);
                break;
            case R.id.colorButton4:
                colorButton.setBackgroundColor(getResources().getColor(R.color.noteColor4));
                toolbar.setBackgroundColor(getResources().getColor(R.color.toolbarNoteColor4));
                dateLayout.setBackgroundColor(getResources().getColor(R.color.noteBgColor4));
                noteEditText.setBackgroundColor(getResources().getColor(R.color.noteBgColor4));
                newNoteViewModel.changeNoteColor(ApplicationConstants.NOTE_BG_GREEN);
                break;
            case R.id.colorButton5:
                colorButton.setBackgroundColor(getResources().getColor(R.color.noteColor5));
                toolbar.setBackgroundColor(getResources().getColor(R.color.toolbarNoteColor5));
                dateLayout.setBackgroundColor(getResources().getColor(R.color.noteBgColor5));
                noteEditText.setBackgroundColor(getResources().getColor(R.color.noteBgColor5));
                newNoteViewModel.changeNoteColor(ApplicationConstants.NOTE_BG_LIGHT_GREEN);
                break;
            case R.id.colorButton6:
                colorButton.setBackgroundColor(getResources().getColor(R.color.noteColor6));
                toolbar.setBackgroundColor(getResources().getColor(R.color.toolbarNoteColor6));
                dateLayout.setBackgroundColor(getResources().getColor(R.color.noteBgColor6));
                noteEditText.setBackgroundColor(getResources().getColor(R.color.noteBgColor6));
                newNoteViewModel.changeNoteColor(ApplicationConstants.NOTE_BG_ORANGE);
                break;
            case R.id.colorButton7:
                colorButton.setBackgroundColor(getResources().getColor(R.color.noteColor7));
                toolbar.setBackgroundColor(getResources().getColor(R.color.toolbarNoteColor7));
                dateLayout.setBackgroundColor(getResources().getColor(R.color.noteBgColor7));
                noteEditText.setBackgroundColor(getResources().getColor(R.color.noteBgColor7));
                newNoteViewModel.changeNoteColor(ApplicationConstants.NOTE_BG_DARK_BLUE);
                break;
            case R.id.colorButton8:
                colorButton.setBackgroundColor(getResources().getColor(R.color.noteColor8));
                toolbar.setBackgroundColor(getResources().getColor(R.color.toolbarNoteColor8));
                dateLayout.setBackgroundColor(getResources().getColor(R.color.noteBgColor8));
                noteEditText.setBackgroundColor(getResources().getColor(R.color.noteBgColor8));
                newNoteViewModel.changeNoteColor(ApplicationConstants.NOTE_BG_BLACK);
                break;
            case R.id.colorButton9:
                colorButton.setBackgroundColor(getResources().getColor(R.color.noteColor9));
                toolbar.setBackgroundColor(getResources().getColor(R.color.toolbarNoteColor9));
                dateLayout.setBackgroundColor(getResources().getColor(R.color.noteBgColor9));
                noteEditText.setBackgroundColor(getResources().getColor(R.color.noteBgColor9));
                newNoteViewModel.changeNoteColor(ApplicationConstants.NOTE_BG_PINK);
                break;
            default:
                System.out.println("no button clicked");
                break;
        }
    }

    @Override
    public void onFailure(String txnStatus, String txnMessage) {

    }

    private void setBackgroundOnUpdate(String noteColor){
        switch(noteColor){
            case ApplicationConstants.NOTE_BG_BLUE:
                colorButton.setBackgroundColor(this.getResources().getColor(R.color.noteColor1));
                toolbar.setBackgroundColor(getResources().getColor(R.color.toolbarNoteColor1));
                dateLayout.setBackgroundColor(getResources().getColor(R.color.noteBgColor1));
                noteEditText.setBackgroundColor(getResources().getColor(R.color.noteBgColor1));
                newNoteViewModel.changeNoteColor(ApplicationConstants.NOTE_BG_BLUE);
                break;
            case ApplicationConstants.NOTE_BG_RED:
                colorButton.setBackgroundColor(getResources().getColor(R.color.noteColor2));
                toolbar.setBackgroundColor(getResources().getColor(R.color.toolbarNoteColor2));
                dateLayout.setBackgroundColor(getResources().getColor(R.color.noteBgColor2));
                noteEditText.setBackgroundColor(getResources().getColor(R.color.noteBgColor2));
                newNoteViewModel.changeNoteColor(ApplicationConstants.NOTE_BG_RED);
                break;
            case ApplicationConstants.NOTE_BG_PURPLE:
                colorButton.setBackgroundColor(getResources().getColor(R.color.noteColor3));
                toolbar.setBackgroundColor(getResources().getColor(R.color.toolbarNoteColor3));
                dateLayout.setBackgroundColor(getResources().getColor(R.color.noteBgColor3));
                noteEditText.setBackgroundColor(getResources().getColor(R.color.noteBgColor3));
                newNoteViewModel.changeNoteColor(ApplicationConstants.NOTE_BG_PURPLE);
                break;
            case ApplicationConstants.NOTE_BG_GREEN:
                colorButton.setBackgroundColor(getResources().getColor(R.color.noteColor4));
                toolbar.setBackgroundColor(getResources().getColor(R.color.toolbarNoteColor4));
                dateLayout.setBackgroundColor(getResources().getColor(R.color.noteBgColor4));
                noteEditText.setBackgroundColor(getResources().getColor(R.color.noteBgColor4));
                newNoteViewModel.changeNoteColor(ApplicationConstants.NOTE_BG_GREEN);
                break;
            case ApplicationConstants.NOTE_BG_LIGHT_GREEN:
                colorButton.setBackgroundColor(getResources().getColor(R.color.noteColor5));
                toolbar.setBackgroundColor(getResources().getColor(R.color.toolbarNoteColor5));
                dateLayout.setBackgroundColor(getResources().getColor(R.color.noteBgColor5));
                noteEditText.setBackgroundColor(getResources().getColor(R.color.noteBgColor5));
                newNoteViewModel.changeNoteColor(ApplicationConstants.NOTE_BG_LIGHT_GREEN);
                break;
            case ApplicationConstants.NOTE_BG_ORANGE:
                colorButton.setBackgroundColor(getResources().getColor(R.color.noteColor6));
                toolbar.setBackgroundColor(getResources().getColor(R.color.toolbarNoteColor6));
                dateLayout.setBackgroundColor(getResources().getColor(R.color.noteBgColor6));
                noteEditText.setBackgroundColor(getResources().getColor(R.color.noteBgColor6));
                newNoteViewModel.changeNoteColor(ApplicationConstants.NOTE_BG_ORANGE);
                break;
            case ApplicationConstants.NOTE_BG_DARK_BLUE:
                colorButton.setBackgroundColor(getResources().getColor(R.color.noteColor7));
                toolbar.setBackgroundColor(getResources().getColor(R.color.toolbarNoteColor7));
                dateLayout.setBackgroundColor(getResources().getColor(R.color.noteBgColor7));
                noteEditText.setBackgroundColor(getResources().getColor(R.color.noteBgColor7));
                newNoteViewModel.changeNoteColor(ApplicationConstants.NOTE_BG_DARK_BLUE);
                break;
            case ApplicationConstants.NOTE_BG_BLACK:
                colorButton.setBackgroundColor(getResources().getColor(R.color.noteColor8));
                toolbar.setBackgroundColor(getResources().getColor(R.color.toolbarNoteColor8));
                dateLayout.setBackgroundColor(getResources().getColor(R.color.noteBgColor8));
                noteEditText.setBackgroundColor(getResources().getColor(R.color.noteBgColor8));
                newNoteViewModel.changeNoteColor(ApplicationConstants.NOTE_BG_BLACK);
                break;
            case ApplicationConstants.NOTE_BG_PINK:
                colorButton.setBackgroundColor(getResources().getColor(R.color.noteColor9));
                toolbar.setBackgroundColor(getResources().getColor(R.color.toolbarNoteColor9));
                dateLayout.setBackgroundColor(getResources().getColor(R.color.noteBgColor9));
                noteEditText.setBackgroundColor(getResources().getColor(R.color.noteBgColor9));
                newNoteViewModel.changeNoteColor(ApplicationConstants.NOTE_BG_PINK);
                break;
            default:
                System.out.println("no button clicked");
                break;
        }
    }
}
