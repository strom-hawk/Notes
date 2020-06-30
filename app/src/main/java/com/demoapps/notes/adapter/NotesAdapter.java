package com.demoapps.notes.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.demoapps.notes.R;
import com.demoapps.notes.model.HomeScreenModel;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Activity context;
    private ArrayList<HomeScreenModel> notesArrayList;

    public NotesAdapter(Activity context, ArrayList<HomeScreenModel> notesArrayList) {
        this.context = context;
        this.notesArrayList = notesArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.notes_layout, parent, false);
        return new RecyclerViewViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HomeScreenModel homeScreenModel = notesArrayList.get(position);
        RecyclerViewViewHolder viewHolder = (RecyclerViewViewHolder) holder;
        viewHolder.noteTitle.setText(homeScreenModel.getNoteTitle());
        viewHolder.noteText.setText(homeScreenModel.getNoteText());
    }

    @Override
    public int getItemCount() {
        return notesArrayList.size();
    }

    class RecyclerViewViewHolder extends RecyclerView.ViewHolder {
        TextView noteTitle;
        TextView noteDate;
        TextView noteText;

        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            noteTitle = itemView.findViewById(R.id.noteTitle);
            noteDate = itemView.findViewById(R.id.noteDate);
            noteText = itemView.findViewById(R.id.noteText);
        }
    }
}