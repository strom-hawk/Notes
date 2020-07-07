package com.demoapps.notes.utils;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = ApplicationConstants.TABLE_NAME)
public class NoteEntity {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "note_title")
    private String noteTitle;

    @ColumnInfo(name = "note_color")
    private String noteColor;

    @ColumnInfo(name = "note_lastUpdated")
    private String lastUpdatedDate;

    @ColumnInfo(name = "note_text")
    private String noteText;

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
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
