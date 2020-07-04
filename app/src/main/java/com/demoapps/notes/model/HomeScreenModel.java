package com.demoapps.notes.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.demoapps.notes.utils.ApplicationConstants;

@Entity(tableName = ApplicationConstants.TABLE_NAME)
public class HomeScreenModel {

    @PrimaryKey
    @NonNull
    private String noteTitle;
    private String noteColor;
    private String lastUpdatedDate;
    private String noteText;

    @NonNull
    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(@NonNull String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteColor() {
        return noteColor;
    }

    public void setNoteColor(String noteColor) {
        this.noteColor = noteColor;
    }

    public String getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(String lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }
}
