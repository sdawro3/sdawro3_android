package com.sdacademy.zientara.rafal.todolist.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sdacademy.zientara.rafal.todolist.R;
import com.sdacademy.zientara.rafal.todolist.models.Note;
import com.sdacademy.zientara.rafal.todolist.viewHolders.NoteViewHolder;

import java.util.List;

/**
 * Created by Evil on 13.07.2017.
 */

public class NotesRecyclerAdapter extends RecyclerView.Adapter<NoteViewHolder> {

    private final LayoutInflater inflater;
    private List<Note> noteList;

    public NotesRecyclerAdapter(List<Note> noteList, Context context) {
        this.noteList = noteList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.note_item, parent, false);
        NoteViewHolder viewHolder = new NoteViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        Note note = noteList.get(position);
        holder.nameTextView.setText(note.getName());
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }
}
