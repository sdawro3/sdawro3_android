package com.sdacademy.zientara.rafal.todolist.adapters.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sdacademy.zientara.rafal.todolist.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Evil on 13.07.2017.
 */

public class NoteViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.noteItem_noteName)
    public TextView nameTextView;

    @BindView(R.id.noteItem_delete)
    public ImageView deleteButton;

    @BindView(R.id.noteItem_moveDown)
    public ImageView moveDown;

    @BindView(R.id.noteItem_moveUp)
    public ImageView moveUp;

    public NoteViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
