package com.sdacademy.zientara.rafal.todolist.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sdacademy.zientara.rafal.todolist.R;
import com.sdacademy.zientara.rafal.todolist.models.Note;
import com.sdacademy.zientara.rafal.todolist.adapters.viewHolders.NoteViewHolder;

import java.util.List;

/**
 * Created by Evil on 13.07.2017.
 */

public class NotesRecyclerAdapter extends RecyclerView.Adapter<NoteViewHolder> {

    private final LayoutInflater inflater;
    private List<Note> noteList;
    private OnNoteClicked onNoteClicked;

    public NotesRecyclerAdapter(List<Note> noteList, Context context, OnNoteClicked onNoteClicked) {
        this.noteList = noteList;
        this.onNoteClicked = onNoteClicked;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.note_item, parent, false);
        NoteViewHolder viewHolder = new NoteViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final NoteViewHolder holder, final int position) {
        final Note note = noteList.get(position);
        holder.nameTextView.setText(note.getName());
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onNoteClicked != null)
                    onNoteClicked.onDeleteClicked(holder.getAdapterPosition());
            }
        });
        holder.moveUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onNoteClicked != null)
                    onNoteClicked.onUpClicked(holder.getAdapterPosition());
            }
        });
        holder.moveDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onNoteClicked != null)
                    onNoteClicked.onDownClicked(holder.getAdapterPosition());
            }
        });
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onNoteClicked != null)
                    onNoteClicked.onEditClicked(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public interface OnNoteClicked {
        void onDeleteClicked(int position);

        void onUpClicked(int position);

        void onDownClicked(int position);

        void onEditClicked(int position);
    }
}
