package com.demoapps.notes.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.demoapps.notes.R;
import com.demoapps.notes.model.HomeScreenModel;
import com.demoapps.notes.model.NewNoteModel;
import com.demoapps.notes.utils.ApplicationConstants;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private OnItemClickListener listener;
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
        viewHolder.noteDate.setText(homeScreenModel.getLastUpdatedDate());
        if (null != homeScreenModel.getNoteColor()) {
            changeNoteBackgroundColor(homeScreenModel.getNoteColor(), viewHolder);
        } else {
            changeNoteBackgroundColor(ApplicationConstants.EMPTY_STRING, viewHolder);
        }


    }


    private void changeNoteBackgroundColor(String backgroundColor, RecyclerViewViewHolder viewHolder) {
        switch (backgroundColor) {
            case ApplicationConstants.NOTE_BG_BLUE:
                viewHolder.parentCardView.setCardBackgroundColor(context.getResources().getColor(R.color.noteBgColor1));
                break;
            case ApplicationConstants.NOTE_BG_RED:
                viewHolder.parentCardView.setCardBackgroundColor(context.getResources().getColor(R.color.noteBgColor2));
                break;
            case ApplicationConstants.NOTE_BG_PURPLE:
                viewHolder.parentCardView.setCardBackgroundColor(context.getResources().getColor(R.color.noteBgColor3));
                break;
            case ApplicationConstants.NOTE_BG_GREEN:
                viewHolder.parentCardView.setCardBackgroundColor(context.getResources().getColor(R.color.noteBgColor4));
                break;
            case ApplicationConstants.NOTE_BG_LIGHT_GREEN:
                viewHolder.parentCardView.setCardBackgroundColor(context.getResources().getColor(R.color.noteBgColor5));
                break;
            case ApplicationConstants.NOTE_BG_ORANGE:
                viewHolder.parentCardView.setCardBackgroundColor(context.getResources().getColor(R.color.noteBgColor6));
                break;
            case ApplicationConstants.NOTE_BG_DARK_BLUE:
                viewHolder.parentCardView.setCardBackgroundColor(context.getResources().getColor(R.color.noteBgColor7));
                break;
            case ApplicationConstants.NOTE_BG_BLACK:
                viewHolder.parentCardView.setCardBackgroundColor(context.getResources().getColor(R.color.noteBgColor8));
                break;
            case ApplicationConstants.NOTE_BG_PINK:
                viewHolder.parentCardView.setCardBackgroundColor(context.getResources().getColor(R.color.noteBgColor9));
                break;
            default:
                viewHolder.parentCardView.setCardBackgroundColor(context.getResources().getColor(R.color.noteBgColor1));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return notesArrayList.size();
    }

    class RecyclerViewViewHolder extends RecyclerView.ViewHolder {
        CardView parentCardView;
        TextView noteTitle;
        TextView noteDate;

        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            noteTitle = itemView.findViewById(R.id.noteTitle);
            noteDate = itemView.findViewById(R.id.noteDate);
            parentCardView = itemView.findViewById(R.id.parentCardView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (null != listener)
                        listener.onItemClick(notesArrayList.get(position));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(HomeScreenModel homeScreenModel);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.listener = onItemClickListener;
    }
}
